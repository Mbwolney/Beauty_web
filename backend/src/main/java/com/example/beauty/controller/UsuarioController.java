package com.example.beauty.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beauty.dto.UsuarioDto;
import com.example.beauty.entity.Usuario;
import com.example.beauty.entity.enums.PerfilEnum;
import com.example.beauty.response.Response;
import com.example.beauty.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	/**
	 * Cadastrar Usuário
	 * 
	 * @param usuario
	 * @param result
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<Usuario>> cadastrarUsuario(@Valid @RequestBody Usuario usuario, BindingResult result)
			throws NoSuchAlgorithmException {
		Response<Usuario> response = new Response<Usuario>();
		validarUsuarioExistente(usuario, result);
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		usuario.setPerfil(PerfilEnum.ROLE_USUARIO);
		service.save(usuario);
		response.setData(usuario);
		return ResponseEntity.created(null).body(response);
	}
	
	/**
	 * Cadastrar Administrador
	 * 
	 * @param usuario
	 * @param result
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping(value = "/admin")
	public ResponseEntity<Response<Usuario>> cadastrarAdmin(@Valid @RequestBody Usuario usuario, BindingResult result)
			throws NoSuchAlgorithmException {
		Response<Usuario> response = new Response<Usuario>();
		validarUsuarioExistente(usuario, result);
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		usuario.setPerfil(PerfilEnum.ROLE_ADMIN);
		service.save(usuario);
		response.setData(usuario);
		return ResponseEntity.created(null).body(response);
	}

	/**
	 * Atualizar Usuário
	 * 
	 * @param id
	 * @param usuarioDto
	 * @param result
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<UsuarioDto>> alterar(@PathVariable("id") Long id,
			@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result) throws NoSuchAlgorithmException {
		Response<UsuarioDto> response = new Response<UsuarioDto>();
		Optional<Usuario> usuario = this.service.findById(id);
		if (!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		this.atualizarDadosUsuario(usuario.get(), usuarioDto, result);
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		this.service.save(usuario.get());
		response.setData(this.converterUsuarioDto(usuario.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * Deletar Usuário
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> deletar(@PathVariable("id") Long id) {
		Response<String> response = new Response<String>();
		Optional<Usuario> usuario = this.service.findById(id);
		if (!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		this.service.delete(usuario.get());
		response.setData("Removido com Sucesso");
		return ResponseEntity.ok(response);
	}

	/**
	 * Retornar Usuário por CPF
	 * 
	 * @param cpf
	 * @return
	 */
	@GetMapping(value = "/cpf/{cpf}")
	public ResponseEntity<Response<UsuarioDto>> findByCpf(@PathVariable("cpf") String cpf) {
		Response<UsuarioDto> response = new Response<UsuarioDto>();
		Optional<Usuario> usuario = this.service.findByCpf(cpf);
		if (!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		response.setData(this.converterUsuarioDto(usuario.get()));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Retornar Usuário por ID
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<UsuarioDto>> findById(@PathVariable("id") Long id) {
		Response<UsuarioDto> response = new Response<UsuarioDto>();
		Optional<Usuario> usuario = this.service.findById(id);
		if (!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		response.setData(this.converterUsuarioDto(usuario.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * Validação se usuário existe
	 * 
	 * @param usuario
	 * @param result
	 */
	private void validarUsuarioExistente(Usuario usuario, BindingResult result) {
		this.service.findByCpf(usuario.getCpf())
				.ifPresent(usu -> result.addError(new ObjectError("usuario", "Usuário já Existente")));
	}

	/**
	 * Atualiza os dados do Usuário apartir do Usuário DTO
	 * 
	 * @param usuario
	 * @param usuarioDto
	 * @param result
	 * @throws NoSuchAlgorithmException
	 */
	private void atualizarDadosUsuario(Usuario usuario, UsuarioDto usuarioDto, BindingResult result)
			throws NoSuchAlgorithmException {
		usuario.setNome(usuarioDto.getNome());
		if (!usuario.getEmail().equals(usuarioDto.getEmail())) {
			this.service.findbyEmail(usuarioDto.getEmail())
					.ifPresent(usu -> result.addError(new ObjectError("email", "Email já existente.")));
			usuario.setEmail(usuarioDto.getEmail());
		}
		if (usuarioDto.getSenha().isPresent()) {
			usuario.setSenha(usuarioDto.getSenha().get());
		}

	}

	/**
	 * Retorna um DTO de Usuário
	 * 
	 * @param usuario
	 * @return
	 */
	private UsuarioDto converterUsuarioDto(Usuario usuario) {
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setId(usuario.getId());
		usuarioDto.setNome(usuario.getNome());
		usuarioDto.setEmail(usuario.getEmail());
		usuarioDto.setPerfil(usuario.getPerfil());
		return usuarioDto;
	}
}

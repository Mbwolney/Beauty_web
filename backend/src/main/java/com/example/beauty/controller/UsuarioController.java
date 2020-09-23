package com.example.beauty.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	 */
	@PostMapping
	public ResponseEntity<Response<Usuario>> cadastrarUsuario(@Valid @RequestBody Usuario usuario,
			BindingResult result) {
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
	
	
	private void validarUsuarioExistente(Usuario usuario, BindingResult result) {
		this.service.findByCpf(usuario.getCpf())
				.ifPresent(usu -> result.addError(new ObjectError("usuario", "Usuário já Existente")));
	}

}

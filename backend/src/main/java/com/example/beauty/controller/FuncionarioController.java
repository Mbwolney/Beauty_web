package com.example.beauty.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
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

import com.example.beauty.dto.FunSerDto;
import com.example.beauty.dto.FuncionarioDto;
import com.example.beauty.dto.ServicoDto;
import com.example.beauty.entity.Funcionario;
import com.example.beauty.entity.Servico;
import com.example.beauty.entity.Usuario;
import com.example.beauty.response.Response;
import com.example.beauty.service.FuncionarioService;
import com.example.beauty.service.ServicoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;

	@Autowired
	private ServicoService servicoService;

	/**
	 * Salvar Funcionário
	 * 
	 * @param funcionario
	 * @param result
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<Funcionario>> cadastrarFuncionario(@Valid @RequestBody Funcionario funcionario,
			BindingResult result) throws NoSuchAlgorithmException {
		Response<Funcionario> response = new Response<Funcionario>();
		validarDadosExistente(funcionario, result);
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		service.save(funcionario);
		response.setData(funcionario);
		return ResponseEntity.created(null).body(response);
	}

	/**
	 * Buscar todos os Funcionários
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity findAll() {
		List<Funcionario> funcionarios = service.getFuncionarios();
		return funcionarios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(funcionarios);
	}

	/**
	 * Deletar Funcionário
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> deletar(@PathVariable("id") Long id) {
		Response<String> response = new Response<String>();
		Optional<Funcionario> funcionario = this.service.findById(id);
		if (!funcionario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		this.service.delete(funcionario.get());
		response.setData("Removido com Sucesso");
		return ResponseEntity.ok(response);
	}

	/**
	 * Alterar Funcionário
	 * @param id
	 * @param funcionarioDto
	 * @param result
	 * @return
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<FunSerDto>> alterar(@PathVariable("id") Long id,
			@Valid @RequestBody FunSerDto funSerDto, BindingResult result) {
		Response<FunSerDto> response = new Response<FunSerDto>();
		
		Optional<Funcionario> funcionarioId = this.service.findById(id);
		if (!funcionarioId.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Funcionario funcionario = this.converterDtoFuncionario(funSerDto);
		Servico servico = this.converterDtoServico(funSerDto);
		List<Servico> servicos = new ArrayList<Servico>();
		servicos.add(servico);
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		this.servicoService.save(servico);
		funcionario.setServico(servicos);
		this.service.save(funcionario);
		//response.setData(servicoId.get());
		return ResponseEntity.ok(response);
	}

	/**
	 * Converter DTO para Funcionário
	 * 
	 * @param funcionario
	 * @param funcionarioDto
	 * @param result
	 * @throws NoSuchAlgorithmException
	 */
	private Funcionario converterDtoFuncionario(FunSerDto funSerDto) {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(funSerDto.getNome());
		return funcionario;
	}

	/**
	 * Converter DTO para Serviço
	 * 
	 * @param funSerDto
	 * @return
	 */
	private Servico converterDtoServico(FunSerDto funSerDto) {
		Servico servico = this.servicoService.findById(funSerDto.getServico()).get();
		return servico;
	}

	/**
	 * Validar se Funcionário Existe
	 * 
	 * @param funcionario
	 * @param result
	 */
	private void validarDadosExistente(Funcionario funcionario, BindingResult result) {
		this.service.findByNome(funcionario.getNome())
				.ifPresent(usu -> result.addError(new ObjectError("funcionario", "Funcionário já Existente")));
	}

}

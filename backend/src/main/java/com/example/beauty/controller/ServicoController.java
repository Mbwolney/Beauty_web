package com.example.beauty.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beauty.dto.ServicoDto;
import com.example.beauty.entity.Servico;
import com.example.beauty.response.Response;
import com.example.beauty.service.ServicoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/servico")
public class ServicoController {

	@Autowired
	private ServicoService service;

	/**
	 * Cadastrar Serviço
	 * 
	 * @param servico
	 * @param result
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<Servico>> cadastrarServico(@Valid @RequestBody Servico servico, BindingResult result)
			throws NoSuchAlgorithmException {
		Response<Servico> response = new Response<Servico>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		service.save(servico);
		response.setData(servico);
		return ResponseEntity.created(null).body(response);
	}

	/**
	 * Deletar um serviço
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> deletar(@PathVariable("id") Long id) {
		Response<String> response = new Response<String>();
		Optional<Servico> servico = this.service.findById(id);
		if (!servico.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		this.service.delete(servico.get());
		response.setData("Removido com Sucesso");
		return ResponseEntity.ok(response);
	}

	/**
	 * Exibi todos os serviços
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity findAll() {
		List<Servico> servico = service.getServico();
		return servico.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(servico);
	}

	/**
	 * Buscar por Id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Servico>> findById(@PathVariable("id") Long id) {
		Response<Servico> response = new Response<Servico>();
		Optional<Servico> servico = this.service.findById(id);
		if (!servico.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		response.setData(servico.get());
		return ResponseEntity.ok(response);
	}

	/**
	 * Atualizar Serviço
	 * 
	 * @param id
	 * @param servicoDto
	 * @param result
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<Servico>> alterar(@PathVariable("id") Long id,
			@Valid @RequestBody ServicoDto servicoDto, BindingResult result) throws NoSuchAlgorithmException {
		Response<Servico> response = new Response<Servico>();
		Optional<Servico> servicoId = this.service.findById(id);
		if (!servicoId.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		this.atualizarDadosServico(servicoId.get(), servicoDto, result);
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		this.service.save(servicoId.get());
		response.setData(servicoId.get());
		return ResponseEntity.ok(response);
	}

	/**
	 * Atualizar dados do Serviço apartir do Serviço DTO
	 * 
	 * @param servico
	 * @param servicoDto
	 * @param result
	 * @throws NoSuchAlgorithmException
	 */
	private void atualizarDadosServico(Servico servico, ServicoDto servicoDto, BindingResult result)
			throws NoSuchAlgorithmException {
		servico.setNome(servicoDto.getNome());
		if (servicoDto.getValor().isPresent()) {
			servico.setValor(servicoDto.getValor().get());
		}
	}

}

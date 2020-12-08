package com.example.beauty.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beauty.dto.AgendamentoDto;
import com.example.beauty.dto.GetAgedamentoDto;
import com.example.beauty.entity.Agendamento;
import com.example.beauty.entity.Funcionario;
import com.example.beauty.entity.Servico;
import com.example.beauty.entity.Usuario;
import com.example.beauty.response.Response;
import com.example.beauty.service.AgendamentoService;
import com.example.beauty.service.FuncionarioService;
import com.example.beauty.service.ServicoService;
import com.example.beauty.service.UsuarioService;

import javassist.Loader.Simple;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/agendamento")
public class AgendamentoController {

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private AgendamentoService service;
	@Autowired
	private FuncionarioService funcionarioService;
	@Autowired
	private ServicoService servicoService;

	/**
	 * Cadastra Agendamento
	 * 
	 * @param id
	 * @param agendamentoDto
	 * @param result
	 * @return
	 * @throws ParseException
	 */
	@PostMapping(value = "/{idUsuario}")
	public ResponseEntity<Response<AgendamentoDto>> cadastrarAgendamento(@PathVariable("idUsuario") Long id,
			@Valid @RequestBody AgendamentoDto agendamentoDto, BindingResult result) throws ParseException {
		Response<AgendamentoDto> response = new Response<AgendamentoDto>();
		Agendamento agendamento = new Agendamento();
		Optional<Usuario> usuario = this.usuarioService.findById(id);
		if (!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		agendamento = this.converterDTOAgendamento(agendamentoDto, usuario.get());
		service.save(agendamento);
		response.setData(agendamentoDto);
		return ResponseEntity.created(null).body(response);
	}

	/**
	 * Exibe todos os agendamentos
	 * 
	 * @return
	 * @throws ParseException
	 */
	@GetMapping
	public ResponseEntity findAll() throws ParseException {
		List<Agendamento> agendamento = this.service.getAgendamento();
		List<GetAgedamentoDto> getAgendamento = new ArrayList<GetAgedamentoDto>();
		for (int i = 0; i < agendamento.size(); i++) {
			getAgendamento.add(this.converterAgendamentogetDto(agendamento.get(i)));
		}
		return getAgendamento.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(getAgendamento);
	}

	/**
	 * Buscar Agendamento por Funcioário
	 * 
	 * @param id
	 * @return
	 * @throws ParseException
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity buscarPorFuncionario(@PathVariable("id") Long id) throws ParseException {
		List<GetAgedamentoDto> getAgendamento = new ArrayList<GetAgedamentoDto>();
		Optional<Funcionario> funcionario = this.funcionarioService.findById(id);
		List<Agendamento> agendamento = this.service.findByFuncionario(funcionario.get());
		for (int i = 0; i < agendamento.size(); i++) {
			getAgendamento.add(this.converterAgendamentogetDto(agendamento.get(i)));
		}
		return getAgendamento.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(getAgendamento);
	}

	/**
	 * Converte um DTO para agendamento
	 * 
	 * @param agendamentoDto
	 * @param usuario
	 * @return
	 * @throws ParseException
	 */
	private Agendamento converterDTOAgendamento(AgendamentoDto agendamentoDto, Usuario usuario) throws ParseException {
		Agendamento agendamento = new Agendamento();
		agendamento.setData(this.dateFormat.parse(agendamentoDto.getData()));
		Optional<Funcionario> funcionario = this.funcionarioService.findById(agendamentoDto.getFuncionario().get());
		agendamento.setFuncionario(funcionario.get());
		Optional<Servico> servico = this.servicoService.findById(agendamentoDto.getServico().get());
		agendamento.setServico(servico.get());
		agendamento.setUsuario(usuario);
		return agendamento;
	}

	/**
	 * Converte Agendamento para Dto
	 * 
	 * @param agendamento
	 * @return
	 * @throws ParseException
	 */
	private GetAgedamentoDto converterAgendamentogetDto(Agendamento agendamento) throws ParseException {
		GetAgedamentoDto agendamentoDto = new GetAgedamentoDto();
		agendamentoDto.setId(agendamento.getId());
		agendamentoDto.setData(this.dateFormat.format(agendamento.getData()));
		agendamentoDto.setUsuario(agendamento.getUsuario().getNome());
		agendamentoDto.setIdFuncionario(agendamento.getFuncionario().getId());
		agendamentoDto.setFuncionario(agendamento.getFuncionario().getNome());
		agendamentoDto.setServico(agendamento.getServico().getNome());
		agendamentoDto.setValor(agendamento.getServico().getValor());
		return agendamentoDto;
	}
}

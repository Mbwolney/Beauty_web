package com.example.beauty.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beauty.entity.Agendamento;
import com.example.beauty.response.Response;
import com.example.beauty.service.AgendamentoService;
import com.example.beauty.service.ServicoService;
import com.example.beauty.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/agendamento")
public class AgendamentoController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private AgendamentoService agendamentoService;
	@Autowired
	private ServicoService servicoService;

	@PostMapping
	public ResponseEntity<Agendamento> cadastrarAgendamento(@Valid @RequestBody Agendamento agendamento,
			BindingResult result) {

		Response<Agendamento> response = new Response<Agendamento>();

		return null;
	}
}

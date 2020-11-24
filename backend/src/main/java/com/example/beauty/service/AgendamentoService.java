package com.example.beauty.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beauty.entity.Agendamento;
import com.example.beauty.entity.Funcionario;
import com.example.beauty.entity.Servico;
import com.example.beauty.repository.AgendamentoRepository;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	/**
	 * Salvar Agendamento
	 * 
	 * @param agendamento
	 */
	public void save(Agendamento agendamento) {
		agendamentoRepository.save(agendamento);
	}

	/**
	 * Buscar por data
	 * 
	 * @param data
	 * @return
	 */
	public Optional<Agendamento> findByData(Date data) {
		return agendamentoRepository.findBydata(data);
	}

	/**
	 * Buscar por Funcionário
	 * 
	 * @param funcionario
	 * @return
	 */
	public Optional<Agendamento> findByFuncionario(Funcionario funcionario) {
		return agendamentoRepository.findByFuncionario(funcionario);
	}

	/**
	 * Buscar por Serviço
	 * 
	 * @param servico
	 * @return
	 */
	public Optional<Agendamento> findByServico(Servico servico) {
		return agendamentoRepository.findByServico(servico);
	}

}

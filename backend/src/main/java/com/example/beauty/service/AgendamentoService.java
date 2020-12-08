package com.example.beauty.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public List<Agendamento> findByFuncionario(Funcionario funcionario) {
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

	/**
	 * Busca todos os Agendamentos
	 * 
	 * @return
	 */
	public List<Agendamento> getAgendamento() {
		return agendamentoRepository.findAll();
	}

}

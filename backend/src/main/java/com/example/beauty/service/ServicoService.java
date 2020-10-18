package com.example.beauty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beauty.entity.Servico;
import com.example.beauty.repository.ServicoRepository;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository servicoRepository;

	/**
	 * Salvar Serviço
	 * 
	 * @param servico
	 */
	public void save(Servico servico) {
		servicoRepository.save(servico);
	}

	/**
	 * Busar todos os Serviços
	 * 
	 * @return
	 */
	public List<Servico> getServico() {
		return servicoRepository.findAll();
	}

	/**
	 * Buscar por ID
	 * 
	 * @param id
	 * @return
	 */
	public Optional<Servico> findById(Long id) {
		return servicoRepository.findById(id);
	}

	/**
	 * Buscar por nome
	 * 
	 * @param nome
	 * @return
	 */
	public Optional<Servico> findByNome(String nome) {
		return servicoRepository.findByNome(nome);
	}

	/**
	 * Deletar Serviço
	 * 
	 * @param servico
	 */
	public void delete(Servico servico) {
		servicoRepository.delete(servico);
	}

}

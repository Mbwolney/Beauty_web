package com.example.beauty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beauty.entity.Funcionario;
import com.example.beauty.entity.Servico;
import com.example.beauty.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	/**
	 * Salvar Funcionário
	 * 
	 * @param funcionario
	 */
	public void save(Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
	}

	/**
	 * Busar todos os Funcionários
	 * 
	 * @return
	 */
	public List<Funcionario> getFuncionarios() {
		return funcionarioRepository.findAll();
	}

	/**
	 * Buscar por ID
	 * 
	 * @param id
	 * @return
	 */
	public Optional<Funcionario> findById(Long id) {
		return funcionarioRepository.findById(id);
	}

	/**
	 * Buscar por nome
	 * 
	 * @param nome
	 * @return
	 */
	public Optional<Funcionario> findByNome(String nome) {
		return funcionarioRepository.findByNome(nome);
	}
	
	public Optional<Funcionario> findByServico(Servico servico){
		return funcionarioRepository.findByServico(servico);
	}

	/**
	 * Deletar Funcionário
	 * 
	 * @param funcionario
	 */
	public void delete(Funcionario funcionario) {
		funcionarioRepository.delete(funcionario);
	}

}

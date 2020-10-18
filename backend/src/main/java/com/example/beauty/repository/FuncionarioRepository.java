package com.example.beauty.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.beauty.entity.Funcionario;
import com.example.beauty.entity.Servico;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	Optional<Funcionario> findByNome(String nome);
	
	Optional<Funcionario> findByServico(Servico servico);
	
}

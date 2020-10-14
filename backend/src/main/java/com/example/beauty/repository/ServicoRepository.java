package com.example.beauty.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.beauty.entity.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

	Optional<Servico> findByNome(String nome);

}

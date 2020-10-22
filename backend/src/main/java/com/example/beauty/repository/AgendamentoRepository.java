package com.example.beauty.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.beauty.entity.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
	
	Optional<Agendamento> findBydata(Date data);
}

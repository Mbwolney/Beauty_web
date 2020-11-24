package com.example.beauty.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.beauty.entity.Agendamento;
import com.example.beauty.entity.Funcionario;
import com.example.beauty.entity.Servico;
import com.example.beauty.entity.Usuario;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
	
	Optional<Agendamento> findBydata(Date data);
	Optional<Agendamento> findByFuncionario(Funcionario funcionario);
	Optional<Agendamento> findByServico(Servico servico);
	Optional<Agendamento> findByUsuario(Usuario usuario);
}

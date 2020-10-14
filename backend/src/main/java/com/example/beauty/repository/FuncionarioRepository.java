package com.example.beauty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.beauty.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}

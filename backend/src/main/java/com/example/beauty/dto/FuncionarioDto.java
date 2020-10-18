package com.example.beauty.dto;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.example.beauty.entity.Servico;

import lombok.Data;

@Data
public class FuncionarioDto {

	private Long id;
	private String nome;
	private List<Servico> servico;
}

package com.example.beauty.dto;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class FuncionarioDto {

	private Long id;
	@NotEmpty(message = "Nome não pode ser Vazio")
	private String nome;
}

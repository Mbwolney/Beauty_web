package com.example.beauty.dto;

import java.math.BigDecimal;
import java.util.Optional;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class ServicoDto {

	private Long id;
	@NotEmpty(message = "Nome não pode ser Vazio")
	private String nome;
	private Optional<BigDecimal> valor = Optional.empty();
}

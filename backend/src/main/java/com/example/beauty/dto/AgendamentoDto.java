package com.example.beauty.dto;

import java.util.Date;
import java.util.Optional;

import lombok.Data;

@Data
public class AgendamentoDto {
	private long id;
	private String data;
	private Optional<Long> servico;
	private Optional<Long> funcionario;
}

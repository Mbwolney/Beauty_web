package com.example.beauty.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class GetAgedamentoDto {
	
	private Long id;
	private String data;
	private String servico;
	private BigDecimal valor;
	private Long idFuncionario;
	private String funcionario;
	private String usuario;

}

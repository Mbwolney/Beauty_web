package com.example.beauty.dto;

import java.util.Optional;

import lombok.Data;

@Data
public class FunSerDto {

	private String nome;
	private Optional<Long> servico = Optional.empty(); 
}

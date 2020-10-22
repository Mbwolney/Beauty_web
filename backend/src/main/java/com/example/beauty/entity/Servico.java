package com.example.beauty.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Servico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Nome não pode ser vazio")
	private String nome;
	private BigDecimal valor;
	@ManyToMany(mappedBy = "servico")
	@JsonIgnore
	private List<Funcionario> funcionario;
	@ManyToMany(mappedBy = "servico")
	@JsonIgnore
	private List<Agendamento> agendamento;

}

package com.example.beauty.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

import com.example.beauty.entity.enums.PerfilEnum;

import lombok.Data;

@Entity
@Data
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cpf;
	@NotEmpty(message = "Email não pode ser vazio.")
	private String email;
	private String senha;
	@Enumerated(EnumType.STRING)
	private PerfilEnum perfil;

}

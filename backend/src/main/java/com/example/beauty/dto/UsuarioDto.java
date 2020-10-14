package com.example.beauty.dto;

import java.util.Optional;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.example.beauty.entity.enums.PerfilEnum;

import lombok.Data;

@Data
public class UsuarioDto {

	private Long id;
	@NotEmpty(message = "Nome não pode ser Vazio")
	private String nome;
	@NotEmpty(message = "Email não pode ser Vazio")
	@Email
	private String email;
	private String cpf;
	private Optional<String> senha = Optional.empty();
	@Enumerated(EnumType.STRING)
	private PerfilEnum perfil;
}

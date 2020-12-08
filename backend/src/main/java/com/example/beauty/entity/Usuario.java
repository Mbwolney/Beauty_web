package com.example.beauty.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import com.example.beauty.entity.enums.PerfilEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Nome não pode ser Vazio")
	private String nome;
	@NotEmpty(message = "CPF não pode ser Vazio")
	@CPF
	private String cpf;
	@NotEmpty(message = "Email não pode ser Vazio")
	@Email
	private String email;
	@NotEmpty(message = "Senha não pode ser Vazio")
	private String senha;
	@Enumerated(EnumType.STRING)
	private PerfilEnum perfil;
	@OneToMany(mappedBy = "usuario")
	@JsonIgnore
	private List<Agendamento> agendamento;

}

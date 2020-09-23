package com.example.beauty.controller;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beauty.entity.Usuario;
import com.example.beauty.entity.enums.PerfilEnum;
import com.example.beauty.response.Response;
import com.example.beauty.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	/**
	 * Cadastrar Usuário
	 * @param usuario
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<Usuario>> cadastrarUsuario(@Valid @RequestBody Usuario usuario)
			throws NoSuchAlgorithmException {
		Response<Usuario> response = new Response<Usuario>();
		usuario.setPerfil(PerfilEnum.ROLE_USUARIO);
		service.save(usuario);
		return ResponseEntity.created(null).body(response);
	}
}

package com.example.beauty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beauty.entity.Usuario;
import com.example.beauty.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
}

package com.example.beauty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beauty.entity.Usuario;
import com.example.beauty.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	/**
	 * Salvar Usuário
	 * 
	 * @param usuario
	 */
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	/**
	 * Buscar Usuário por CPF
	 * 
	 * @param cpf
	 * @return
	 */
	public Optional<Usuario> findByCpf(String cpf) {
		return usuarioRepository.findByCpf(cpf);
	}

	/**
	 * Buscar Usuário por ID
	 * 
	 * @param id
	 * @return
	 */
	public Optional<Usuario> findById(Long id) {
		return usuarioRepository.findById(id);
	}

	/**
	 * Buscar Usuário por Email
	 * 
	 * @param email
	 * @return
	 */
	public Optional<Usuario> findbyEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	/**
	 * Deletar Usuário
	 * 
	 * @param usuario
	 */
	public void delete(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}

	/**
	 * Buscar todos os Usuários
	 * 
	 * @param usuario
	 * @return
	 */
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

}

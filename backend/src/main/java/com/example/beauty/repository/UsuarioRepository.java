package com.example.beauty.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.beauty.entity.Usuario;



public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);
	Optional<Usuario> findByCpf(String cpf);
	Optional<Usuario> findById(Long id);
	List<Usuario> findAll();
}

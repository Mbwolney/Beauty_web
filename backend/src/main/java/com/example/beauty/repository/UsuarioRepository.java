package com.example.beauty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.beauty.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}

package com.example.Mercado.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Mercado.model.UsuarioModel;

public interface UsuarioRepository  extends JpaRepository<UsuarioModel, Long >{
    Optional<UsuarioModel> findByEmail(String email);
}


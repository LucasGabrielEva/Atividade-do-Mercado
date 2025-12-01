package com.example.Mercado.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Mercado.model.ClienteModel;

public interface ClienteRepository  extends JpaRepository<ClienteModel, Long >{
    Optional<ClienteModel> findByEmail(String email);
}


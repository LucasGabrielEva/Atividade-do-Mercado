package com.example.Mercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Mercado.model.ProdutoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
}

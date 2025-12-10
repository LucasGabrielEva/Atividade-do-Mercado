/* ============================================================
   CRIAÇÃO DO BANCO DE DADOS
============================================================ */
DROP DATABASE IF EXISTS mercado;

CREATE DATABASE mercado
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE mercado;


/* ============================================================
   TABELA: USUÁRIOS
============================================================ */
DROP TABLE IF EXISTS tab_usuario;

CREATE TABLE tab_usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(150) NOT NULL,
  email VARCHAR(150) UNIQUE NOT NULL,
  senha VARCHAR(255) NOT NULL,
  telefone VARCHAR(30),
  forma_pagamento VARCHAR(50)
);


/* ============================================================
   TABELA: PRODUTOS
============================================================ */
DROP TABLE IF EXISTS tab_produto;

CREATE TABLE tab_produto (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(200) NOT NULL,
  preco DECIMAL(10,2) NOT NULL,
  quantidade INT NOT NULL DEFAULT 0,
  imagem VARCHAR(500) NOT NULL,
  descricao TEXT

);


/* ============================================================
   CONSULTAS
============================================================ */

-- Mostrar todos os usuários
SELECT * FROM tab_usuario;

-- Mostrar todos os produtos
SELECT * FROM tab_produto;

-- Produtos com estoque baixo
SELECT nome, preco, quantidade, imagem 
FROM tab_produto 
WHERE quantidade < 50;-

-- Listar tabelas criadas
SHOW TABLES;


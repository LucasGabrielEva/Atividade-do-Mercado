/* ============================================================
   CRIAÇÃO DO BANCO DE DADOS
============================================================ */
DROP DATABASE IF EXISTS mercado;      -- Apaga o banco "mercado" caso já exista

CREATE DATABASE mercado
  CHARACTER SET utf8mb4               -- Suporte total a emojis e caracteres especiais
  COLLATE utf8mb4_unicode_ci;         -- Ordenação mais correta para português

USE mercado;                           -- Seleciona o banco para uso


/* ============================================================
   TABELA: USUÁRIOS
============================================================ */
DROP TABLE IF EXISTS tab_usuario;        -- Remove a tabela caso já exista

CREATE TABLE tab_usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,     -- Identificador único (chave primária)
  nome VARCHAR(150) NOT NULL,            -- Nome completo do usuário
  email VARCHAR(150) UNIQUE NOT NULL,    -- E-mail único no sistema
  senha VARCHAR(255) NOT NULL,           -- Senha criptografada
  telefone VARCHAR(30),                  -- Telefone opcional
  forma_pagamento VARCHAR(50)
);


/* ============================================================
   TABELA: PRODUTOS
============================================================ */
DROP TABLE IF EXISTS tab_produto;       -- Remove a tabela, se existir

CREATE TABLE tab_produto (
  id INT AUTO_INCREMENT PRIMARY KEY,    -- Identificador único
  nome VARCHAR(200) NOT NULL,           -- Nome do produto
  preco DECIMAL(10,2) NOT NULL,         -- Preço com 2 casas decimais
  quantidade INT NOT NULL DEFAULT 0,    -- Estoque inicial (padrão 0)
  imagem VARCHAR(500) NOT NULL,         -- URL da imagem do produto
  descricao TEXT                        -- Detalhes do produto

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


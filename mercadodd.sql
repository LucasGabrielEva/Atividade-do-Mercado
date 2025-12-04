/* ============================================================
   CRIAÇÃO DO BANCO DE DADOS
============================================================ */
DROP DATABASE IF EXISTS mercado;

CREATE DATABASE mercado
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE mercado;


/* ============================================================
   TABELA: CLIENTES
============================================================ */
CREATE TABLE tab_cliente (
  id_cliente INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(150) NOT NULL,
  telefone VARCHAR(30) NOT NULL,
  email VARCHAR(150) UNIQUE NOT NULL,
  senha VARCHAR(255) NOT NULL,
  data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP,
  ativo BOOLEAN DEFAULT TRUE
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
  descricao TEXT
);


-- Mostra todos os clientes
SELECT * FROM tab_cliente;

-- Mostra todos os produtos
SELECT * FROM tab_produto;

-- Mostra produtos com estoque baixo (menos de 50 unidades) - CORRIGIDO
SELECT nome, preco, quantidade 
FROM tab_produto 
WHERE quantidade < 50;
-- Removido: AND ativo = TRUE (pois a tabela produto não tem essa coluna)

-- Mostra clientes ativos
SELECT nome, telefone, email 
FROM tab_cliente 
WHERE ativo = TRUE;


/* ============================================================
   MOSTRAR TABELAS CRIADAS
============================================================ */
SHOW TABLES;

select * from tab_produto;

select * from tab_usuario;

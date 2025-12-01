/* ============================================================
   APAGA E CRIA O BANCO DE DADOS
============================================================ */
DROP DATABASE IF EXISTS sistema_mercado01;

CREATE DATABASE sistema_mercado01
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE sistema_mercado01;


/* ============================================================
   TABELA: CLIENTES
============================================================ */
CREATE TABLE clientes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(150) NOT NULL,
  cpf_cnpj VARCHAR(20) UNIQUE,
  email VARCHAR(150),
  telefone VARCHAR(30),
  endereco VARCHAR(255),
  cidade VARCHAR(100),
  estado CHAR(2),
  cep VARCHAR(20),
  criado_em DATETIME DEFAULT CURRENT_TIMESTAMP
);


/* ============================================================
   TABELA: CATEGORIAS
============================================================ */
CREATE TABLE categorias (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL UNIQUE,
  descricao VARCHAR(255)
);


/* ============================================================
   TABELA: FORNECEDORES
============================================================ */
CREATE TABLE fornecedores (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(150) NOT NULL,
  contato VARCHAR(150),
  telefone VARCHAR(50),
  email VARCHAR(150),
  endereco VARCHAR(255),
  cidade VARCHAR(100),
  estado CHAR(2),
  criado_em DATETIME DEFAULT CURRENT_TIMESTAMP
);


/* ============================================================
   TABELA: PRODUTOS
============================================================ */
CREATE TABLE produtos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  sku VARCHAR(50) UNIQUE,
  nome VARCHAR(200) NOT NULL,
  descricao TEXT,
  preco_custo DECIMAL(10,2) DEFAULT 0.00,
  preco_venda DECIMAL(10,2) NOT NULL,
  categoria_id INT,
  fornecedor_id INT,
  ativo BOOLEAN DEFAULT TRUE,
  criado_em DATETIME DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT fk_produtos_categoria 
      FOREIGN KEY (categoria_id) REFERENCES categorias(id)
      ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT fk_produtos_fornecedor 
      FOREIGN KEY (fornecedor_id) REFERENCES fornecedores(id)
      ON DELETE SET NULL ON UPDATE CASCADE
);


/* ============================================================
   TABELA: USUÁRIOS
============================================================ */
CREATE TABLE usuarios (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(150) NOT NULL,
  usuario VARCHAR(80) UNIQUE NOT NULL,
  senha_hash VARCHAR(255) NOT NULL,
  email VARCHAR(150),
  funcao VARCHAR(80),
  ativo BOOLEAN DEFAULT TRUE,
  criado_em DATETIME DEFAULT CURRENT_TIMESTAMP
);


/* ============================================================
   TABELA: VENDAS
============================================================ */
CREATE TABLE vendas (
  id INT AUTO_INCREMENT PRIMARY KEY,
  data_venda DATETIME DEFAULT CURRENT_TIMESTAMP,
  cliente_id INT,
  usuario_id INT NOT NULL,
  total DECIMAL(12,2) NOT NULL DEFAULT 0.00,
  status ENUM('ABERTA','FINALIZADA','CANCELADA') DEFAULT 'ABERTA',
  observacoes TEXT,

  CONSTRAINT fk_vendas_cliente
      FOREIGN KEY (cliente_id) REFERENCES clientes(id)
      ON DELETE SET NULL ON UPDATE CASCADE,

  CONSTRAINT fk_vendas_usuario
      FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
      ON DELETE RESTRICT ON UPDATE CASCADE
);


/* ============================================================
   TABELA: ITENS DA VENDA
============================================================ */
CREATE TABLE itens_venda (
  id INT AUTO_INCREMENT PRIMARY KEY,
  venda_id INT NOT NULL,
  produto_id INT NOT NULL,
  quantidade INT NOT NULL DEFAULT 1,
  preco_unitario DECIMAL(10,2) NOT NULL,

  subtotal DECIMAL(12,2)
      AS (quantidade * preco_unitario) VIRTUAL,

  CONSTRAINT fk_itensvenda_venda
      FOREIGN KEY (venda_id) REFERENCES vendas(id)
      ON DELETE CASCADE,

  CONSTRAINT fk_itensvenda_produto
      FOREIGN KEY (produto_id) REFERENCES produtos(id)
      ON DELETE RESTRICT
);


/* ============================================================
   TABELA: PAGAMENTOS
============================================================ */
CREATE TABLE pagamentos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  venda_id INT NOT NULL,
  data_pagamento DATETIME DEFAULT CURRENT_TIMESTAMP,
  metodo ENUM('DINHEIRO','CARTAO_CREDITO','CARTAO_DEBITO','PIX','OUTRO') DEFAULT 'DINHEIRO',
  valor_pagamento DECIMAL(12,2) NOT NULL,
  referencia VARCHAR(150),

  CONSTRAINT fk_pagamentos_venda
      FOREIGN KEY (venda_id) REFERENCES vendas(id)
      ON DELETE CASCADE
);


/* ============================================================
   TABELA: COMPRAS
============================================================ */
CREATE TABLE compras (
  id INT AUTO_INCREMENT PRIMARY KEY,
  fornecedor_id INT,
  data_compra DATETIME DEFAULT CURRENT_TIMESTAMP,
  valor_total DECIMAL(12,2) DEFAULT 0.00,
  status ENUM('PEDIDO','RECEBIDO','CANCELADO') DEFAULT 'PEDIDO',

  CONSTRAINT fk_compras_fornecedor
      FOREIGN KEY (fornecedor_id) REFERENCES fornecedores(id)
      ON DELETE SET NULL
);


/* ============================================================
   TABELA: ITENS DA COMPRA
============================================================ */
CREATE TABLE itens_compra (
  id INT AUTO_INCREMENT PRIMARY KEY,
  compra_id INT NOT NULL,
  produto_id INT NOT NULL,
  quantidade INT NOT NULL,
  preco_unitario DECIMAL(10,2) NOT NULL,

  subtotal DECIMAL(12,2)
      AS (quantidade * preco_unitario) VIRTUAL,

  CONSTRAINT fk_itenscompra_compra
      FOREIGN KEY (compra_id) REFERENCES compras(id)
      ON DELETE CASCADE,

  CONSTRAINT fk_itenscompra_produto
      FOREIGN KEY (produto_id) REFERENCES produtos(id)
      ON DELETE RESTRICT
);


/* ============================================================
   TABELA: ESTOQUE
============================================================ */
CREATE TABLE estoque (
  produto_id INT PRIMARY KEY,
  quantidade INT NOT NULL DEFAULT 0,

  CONSTRAINT fk_estoque_produto
      FOREIGN KEY (produto_id) REFERENCES produtos(id)
      ON DELETE CASCADE
);


/* ============================================================
   TRIGGERS DE CONTROLE DE ESTOQUE
============================================================ */
DELIMITER $$

CREATE TRIGGER trg_venda_baixa_estoque
AFTER INSERT ON itens_venda
FOR EACH ROW
BEGIN
  UPDATE estoque
  SET quantidade = quantidade - NEW.quantidade
  WHERE produto_id = NEW.produto_id;
END$$


CREATE TRIGGER trg_compra_sobe_estoque
AFTER INSERT ON itens_compra
FOR EACH ROW
BEGIN
  UPDATE estoque
  SET quantidade = quantidade + NEW.quantidade
  WHERE produto_id = NEW.produto_id;
END$$

DELIMITER ;


/* ============================================================
   DADOS INICIAIS (SEM ERROS)
============================================================ */

INSERT IGNORE INTO categorias (nome, descricao) VALUES
 ('Hortifruti','Frutas e verduras'),
 ('Bebidas','Sucos, refrigerantes e água'),
 ('Mercearia','Produtos secos e enlatados');

INSERT IGNORE INTO fornecedores (nome, contato, telefone, email) VALUES
 ('Fornecedor A','João','(11) 99999-0001','fornA@exemplo.com'),
 ('Fornecedor B','Maria','(11) 99999-0002','fornB@exemplo.com');

INSERT IGNORE INTO produtos (sku,nome,descricao,preco_custo,preco_venda,categoria_id,fornecedor_id) VALUES
 ('SKU-001','Banana Prata','Cacho de banana',0.50,1.50,1,1),
 ('SKU-002','Refrigerante Cola 2L','Refrigerante 2L',2.00,6.50,2,2),
 ('SKU-003','Arroz 5kg','Arroz branco',10.00,18.50,3,2);


/* ESTOQUE INICIAL */
INSERT INTO estoque (produto_id, quantidade)
SELECT id, 100 FROM produtos;


/* USUÁRIO */
INSERT IGNORE INTO usuarios (nome, usuario, senha_hash, email, funcao) VALUES
 ('Administrador','admin','$2y$10$EXEMPLOHASH','admin@mercado.com','ADMIN');


/* CLIENTE */
INSERT IGNORE INTO clientes (nome, cpf_cnpj, email, telefone) VALUES
 ('Cliente Exemplo','11122233344','cliente@ex.com','(11) 99999-1111');


/* ============================================================
   VENDA DE TESTE
============================================================ */

INSERT INTO vendas (cliente_id, usuario_id, total, status)
VALUES (1,1,0.00,'ABERTA');

SET @venda_id = LAST_INSERT_ID();

INSERT INTO itens_venda (venda_id,produto_id,quantidade,preco_unitario) VALUES
(@venda_id, 1, 5, 1.50),
(@venda_id, 2, 1, 6.50);

/* Atualizar total da venda sem erro 1175 */
SET SQL_SAFE_UPDATES = 0;

UPDATE vendas
SET total = (SELECT SUM(quantidade * preco_unitario) FROM itens_venda WHERE venda_id = @venda_id),
    status = 'FINALIZADA'
WHERE id = @venda_id;

INSERT INTO pagamentos (venda_id, metodo, valor_pagamento)
VALUES (@venda_id, 'DINHEIRO', (SELECT total FROM vendas WHERE id = @venda_id));

SET SQL_SAFE_UPDATES = 1;


/* ============================================================
   MOSTRAR TABELAS
============================================================ */
SHOW TABLES;
package com.example.Mercado.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tab_produto")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @Column(nullable = false, unique = true)
    private String nome;
    
    @Column(nullable = false)
    private Double preco;
    
    @Column(nullable = false)
    private String quantidade;
    
     @Column(nullable = false)
    private String descricao;
    
    @Column(name = "imagem")
    private String  imagem;
    
    
}

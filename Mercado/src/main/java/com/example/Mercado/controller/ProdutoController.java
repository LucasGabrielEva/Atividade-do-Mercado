package com.example.Mercado.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.Mercado.dto.ProdutoRequestDTO;
import com.example.Mercado.dto.ProdutoResponseDTO;
import com.example.Mercado.service.ProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")

public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody ProdutoRequestDTO dto) {
        produtoService.salvaProduto(dto);
        return ResponseEntity
        .ok()
        .body(Map.of(
            "mensagem", "cadastrado com sucesso",
            "sucesses", true
        ));
    }

      @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listar() {
        return ResponseEntity
        .ok()
        .body(produtoService.listaTodos());

    }

     @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizar(
        @PathVariable Long id,
        @Valid @RequestBody ProdutoRequestDTO dto) {
        
            produtoService.atualizarProduto(id, dto);

        return ResponseEntity
        .ok()
        .body(Map.of(
            "mensagem", "atualizado com sucesso",
            "sucesses", true
        ));
        }

         @DeleteMapping("/{id}")
        public ResponseEntity<Map<String, Object>> deletar(@PathVariable Long id) {
            produtoService.deletarProduto(id);
            return ResponseEntity
            .ok()
            .body(Map.of(
                "mensagem", "excluir com sucesso",
                "sucesses", true
            ));
        }



    
}
















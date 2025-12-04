package com.example.Mercado.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Mercado.dto.UsuarioRequestDTO;
import com.example.Mercado.dto.UsuarioResponseDTO;

import com.example.Mercado.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:5173")

public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody UsuarioRequestDTO dto) {
        usuarioService.salvarCliente(dto);
        return ResponseEntity
        .ok()
        .body(Map.of(
            "mensagem", "cadastrado com sucesso",
            "sucesses", true
        ));
    }
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        return ResponseEntity
        .ok()
        .body(usuarioService.listaTodos());

    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizar(
        @PathVariable Long id,
        @Valid @RequestBody UsuarioRequestDTO dto) {
        
            usuarioService.atualizarCliente(id, dto);

        return ResponseEntity
        .ok()
        .body(Map.of(
            "mensagem", "atualizado com sucesso",
            "sucesses", true
        ));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Map<String, Object>> deletar(@PathVariable Long id) {
            usuarioService.deletarUsuario(id);
            return ResponseEntity
            .ok()
            .body(Map.of(
                "mensagem", "excluir com sucesso",
                "sucesses", true
            ));
        }
    
}

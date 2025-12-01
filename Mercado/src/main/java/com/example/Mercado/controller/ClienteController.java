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

import com.example.Mercado.dto.ClienteRequestDTO;
import com.example.Mercado.dto.ClienteResponseDTO;
import com.example.Mercado.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody ClienteRequestDTO dto) {
        clienteService.salvarCliente(dto);
        return ResponseEntity
        .created(null)
        .body(Map.of(
            "mensagem", "cadastrado com sucesso",
            "sucesses", true
        ));
    }
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() {
        return ResponseEntity
        .ok()
        .body(clienteService.listaTodos());

    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizar(
        @PathVariable Long id,
        @Valid @RequestBody ClienteRequestDTO dto) {
        
            clienteService.atualozarCliente(id, dto);

        return ResponseEntity
        .ok()
        .body(Map.of(
            "mensagem", "atualizado com sucesso",
            "sucesses", true
        ));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Map<String, Object>> deletar(@PathVariable Long id) {
            clienteService.deletarCliente(id);
            return ResponseEntity
            .ok()
            .body(Map.of(
                "mensagem", "excluir com sucesso",
                "sucesses", true
            ));
        }
    
}

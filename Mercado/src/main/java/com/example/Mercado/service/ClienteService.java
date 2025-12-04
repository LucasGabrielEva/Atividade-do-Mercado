package com.example.Mercado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Mercado.dto.ClienteRequestDTO;
import com.example.Mercado.dto.ClienteResponseDTO;
import com.example.Mercado.model.ClienteModel;
import com.example.Mercado.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //consultar Usuarios
    public List<ClienteResponseDTO> listaTodos() {
        return clienteRepository
                .findAll()
                .stream()
                .map(u -> new ClienteResponseDTO(u.getNome(), u.getEmail()))
                .toList();
    }
    // Salva Usuario
    public ClienteModel salvarCliente(ClienteRequestDTO dto) {
        if (clienteRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Cliente já cadastrado.");
        }

    ClienteModel novoCliente = new ClienteModel();
    novoCliente.setNome(dto.getNome());
    novoCliente.setEmail(dto.getEmail());
    novoCliente.setTelefone(dto.getTelefone());
    novoCliente.setSenha(bCryptPasswordEncoder.encode(dto.getSenha()));

    clienteRepository.save(novoCliente); // salvando no banco de dados
    return novoCliente;
    }
   //update
    public ClienteModel atualizarCliente(Long id, ClienteRequestDTO dto) {
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
        ClienteModel atualizarCliente = new ClienteModel();
        atualizarCliente.setId(id);
        atualizarCliente.setNome(dto.getNome());
        atualizarCliente.setEmail(dto.getEmail());
        atualizarCliente.setTelefone(dto.getTelefone());
        atualizarCliente.setSenha(bCryptPasswordEncoder.encode(dto.getSenha()));

        clienteRepository.save(atualizarCliente);
        return atualizarCliente;
    }

    //delete

    public void deletarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado.");
        }
        clienteRepository.deleteById(id);
    }
    
    
    
    

    
}

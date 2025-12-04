package com.example.Mercado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Mercado.dto.UsuarioRequestDTO;
import com.example.Mercado.dto.UsuarioResponseDTO;
import com.example.Mercado.model.UsuarioModel;
import com.example.Mercado.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //consultar Usuarios
    public List<UsuarioResponseDTO> listaTodos() {
        return usuarioRepository
                .findAll()
                .stream()
                .map(u -> new UsuarioResponseDTO(u.getNome(), u.getEmail(), u.getTelefone()))
                .toList();
    }
    // Salva Usuario
    public UsuarioModel salvarCliente(UsuarioRequestDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Usuario já cadastrado.");
        }

    UsuarioModel novoUsuario = new UsuarioModel();
    novoUsuario.setNome(dto.getNome());
    novoUsuario.setEmail(dto.getEmail());
    novoUsuario.setTelefone(dto.getTelefone());
    novoUsuario.setSenha(bCryptPasswordEncoder.encode(dto.getSenha()));

    usuarioRepository.save(novoUsuario); // salvando no banco de dados
    return novoUsuario;
    }
   //update
    public UsuarioModel atualizarCliente(Long id, UsuarioRequestDTO dto) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario não encontrado.");
        }
        UsuarioModel atualizarUsuario = new UsuarioModel();
        atualizarUsuario.setId(id);
        atualizarUsuario.setNome(dto.getNome());
        atualizarUsuario.setEmail(dto.getEmail());
        atualizarUsuario.setTelefone(dto.getTelefone());
        atualizarUsuario.setSenha(bCryptPasswordEncoder.encode(dto.getSenha()));

        usuarioRepository.save(atualizarUsuario);
        return atualizarUsuario;
    }

    //delete

    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario não encontrado.");
        }
        usuarioRepository.deleteById(id);
    }
    
    
    
    

    
}

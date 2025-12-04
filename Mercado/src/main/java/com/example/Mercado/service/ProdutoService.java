package com.example.Mercado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Mercado.dto.ProdutoRequestDTO;
import com.example.Mercado.dto.ProdutoResponseDTO;
import com.example.Mercado.model.ProdutoModel;
import com.example.Mercado.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    //consultar Produtos
    public List<ProdutoResponseDTO> listaTodos() {
        return produtoRepository
                .findAll()
                .stream()
                .map(p -> new ProdutoResponseDTO(p.getNome(), p.getPreco()))
                .toList();

    }

    //Salvar Produto
    public  ProdutoModel salvaProduto(ProdutoRequestDTO dto) {
        if (produtoRepository.findByNome (dto.getNome()).isPresent()) {
            throw new IllegalArgumentException("Produto já cadastrado.");
        }
        
        ProdutoModel novoProduto = new ProdutoModel();
        novoProduto.setNome(dto.getNome());
        novoProduto.setPreco(dto.getPreco());
        novoProduto.setQuantidade(dto.getQuantidade());
        novoProduto.setDescricao(dto.getDescricao());
        
        produtoRepository.save(novoProduto);
        return novoProduto;


    }

    //update
    public ProdutoModel atualizarProduto(Long id, ProdutoRequestDTO dto) {
        if (!produtoRepository.existsById(id)) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }
        ProdutoModel atualizarProduto = new ProdutoModel();
        atualizarProduto.setId(id);
        atualizarProduto.setNome(dto.getNome());
        atualizarProduto.setPreco(dto.getPreco());
        atualizarProduto.setQuantidade(dto.getQuantidade());
        atualizarProduto.setDescricao(dto.getDescricao());

        produtoRepository.save(atualizarProduto);
        return atualizarProduto;
    }

    //delete
    public void deletarProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }
        produtoRepository.deleteById(id);
    }



    
}

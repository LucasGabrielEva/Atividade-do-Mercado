package com.example.Mercado.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProdutoRequestDTO {

     @NotBlank(message = "O nome do produto é obrigatório.")
    @Size(min = 3, message = "O nome do produto deve conter no mínimo 3 caracteres.")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória.")
    @Size(min = 5, message = "A descrição deve conter no mínimo 5 caracteres.")
    private String descricao;

    @NotNull(message = "O preço é obrigatório.")
    @Positive(message = "O preço deve ser maior que zero.")
    private Double preco;

    @NotNull(message = "A quantidade é obrigatória.")
    @Positive(message = "A quantidade deve ser maior que zero.")
    private String quantidade;


    
}

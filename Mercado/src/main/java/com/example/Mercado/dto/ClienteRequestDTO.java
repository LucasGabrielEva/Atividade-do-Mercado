package com.example.Mercado.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteRequestDTO {
    @NotBlank( message = "O nome é obrigatório. ")
    @Size(min = 3, message = "O nome deve conter no mínimo 3 caracteres. ")
    private String nome;

    @NotBlank( message = "O email é obrigatório. ")
    @Email( message = "O email deve ser válido. ")
    private String email;

    @NotBlank( message = "O telefone é obrigatório. ")
    private String telefone;

    @NotBlank( message = "A senha é obrigatória. ")
    @Size(min = 6, max = 20, message = "A senha deve conter entre 6 a 20  caracteres. ")
    private String senha;



}

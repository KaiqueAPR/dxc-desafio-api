package com.kaiqueapr.dxcdesafioapi.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class CriarClienteRequestDto {

    @NotBlank(message = "Nome do Cliente deve ser preenchido!")
    @NotNull(message = "Nome do Cliente não pode ser nulo!")
    @Size(max = 200, message = "Nome do Cliente deve conter no máximo 200 caracteres!")
    private String nmCliente;

    @NotBlank(message = "Email deve ser preenchido!")
    @NotNull(message = "Email não pode ser nulo!")
    @Email(message = "Email inválido!")
    @Size(max = 200, message = "Email deve ter no máximo 200 caracteres!")
    private String nmEmail;

    @NotBlank(message = "Telefone deve ser preenchido!")
    @NotNull(message = "Telefone não pode ser nulo!")
    @Size(min = 10, max = 11, message = "Telefone deve conter entre 10 e 11 números!")
    private String nrTelefone;

    @NotBlank(message = "Senha deve ser preenchido!")
    @NotNull(message = "Senha não pode ser nulo!")
    @Size(max = 20, message = "Senha deve ter no máximo 20 caracteres!")
    private String cdSenha;


}

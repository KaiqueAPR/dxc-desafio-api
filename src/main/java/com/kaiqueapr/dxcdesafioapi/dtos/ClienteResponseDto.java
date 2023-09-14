package com.kaiqueapr.dxcdesafioapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ClienteResponseDto {

    private Integer cdCliente;
    private String nmCliente;
    private String nmEmail;
    private String nrTelefone;

}

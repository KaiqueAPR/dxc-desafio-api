package com.kaiqueapr.dxcdesafioapi.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "cliente")
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIE_CD_CLIENTE")
    private Integer cdCliente;

    @Column(name = "CLIE_NM_CLIENTE")
    private String nmCliente;

    @Column(name = "CLIE_NM_EMAIL")
    private String nmEmail;

    @Column(name = "CLIE_NR_TELEFONE")
    private String nrTelefone;

    @Column(name = "CLIE_CD_SENHA")
    private String cdSenha;

}

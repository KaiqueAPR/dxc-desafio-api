package com.kaiqueapr.dxcdesafioapi.controllers;

import com.kaiqueapr.dxcdesafioapi.dtos.ClienteResponseDto;
import com.kaiqueapr.dxcdesafioapi.dtos.CriarClienteRequestDto;
import com.kaiqueapr.dxcdesafioapi.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteResponseDto> lista(){
        return clienteService.listaClientes();
    }

    @GetMapping("/{cdCliente}")
    public ClienteResponseDto acharClientePorId(Integer cdCliente){
        return clienteService.acharClientePorId(cdCliente);
    }

    @PostMapping("/novoCliente")
    public ClienteResponseDto novoCliente(CriarClienteRequestDto clienteRequest) {
        return clienteService.novoCliente(clienteRequest);
    }

    @PutMapping("/{cdCliente}")
    public ClienteResponseDto updatePorId(@RequestBody CriarClienteRequestDto criarClienteRequestDto, @PathVariable("cdCliente") Integer cdCliente){
        return clienteService.updatePorId(criarClienteRequestDto, cdCliente);
    }

    @DeleteMapping("/{cdCliente}")
    public void deletePorId(@PathVariable("cdCliente") Integer cdCliente){
        clienteService.deletarCliente(cdCliente);
    }

}

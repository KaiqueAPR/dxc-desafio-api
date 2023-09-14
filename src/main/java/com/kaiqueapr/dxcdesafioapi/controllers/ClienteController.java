package com.kaiqueapr.dxcdesafioapi.controllers;

import com.kaiqueapr.dxcdesafioapi.dtos.ClienteResponseDto;
import com.kaiqueapr.dxcdesafioapi.dtos.CriarClienteRequestDto;
import com.kaiqueapr.dxcdesafioapi.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteResponseDto> lista(){
        return clienteService.listaClientes();
    }

    @GetMapping("/{cdCliente}")
    public ClienteResponseDto acharClientePorId(@PathVariable("cdCliente") Integer cdCliente){
        return clienteService.acharClientePorId(cdCliente);
    }

    @PostMapping("/novo")
    public ClienteResponseDto novoCliente(@RequestBody @Valid CriarClienteRequestDto clienteRequest) {
        return clienteService.novoCliente(clienteRequest);
    }

    @PutMapping("/{cdCliente}")
    public ClienteResponseDto updatePorId(@RequestBody @Valid CriarClienteRequestDto criarClienteRequestDto, @PathVariable("cdCliente") Integer cdCliente){
        return clienteService.updatePorId(criarClienteRequestDto, cdCliente);
    }

    @DeleteMapping("/{cdCliente}")
    public void deletePorId(@PathVariable("cdCliente") Integer cdCliente){
        clienteService.deletarCliente(cdCliente);
    }

}

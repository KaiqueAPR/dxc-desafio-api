package com.kaiqueapr.dxcdesafioapi.controllers;

import com.kaiqueapr.dxcdesafioapi.dtos.ClienteResponseDto;
import com.kaiqueapr.dxcdesafioapi.dtos.CriarClienteRequestDto;
import com.kaiqueapr.dxcdesafioapi.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cliente")
public class ClienteController {

private final ClienteService clienteService;

public ClienteController(ClienteService clienteService) {
    this.clienteService = clienteService;
}

@GetMapping
public Page<ClienteResponseDto> lista(@PageableDefault(page = 0, size = 10, sort = "cdCliente", direction = Sort.Direction.ASC) Pageable pageable){
    return clienteService.listaClientes(pageable);
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

package com.kaiqueapr.dxcdesafioapi.services;

import com.kaiqueapr.dxcdesafioapi.configs.exceptions.UserNotFound;
import com.kaiqueapr.dxcdesafioapi.dtos.ClienteResponseDto;
import com.kaiqueapr.dxcdesafioapi.dtos.CriarClienteRequestDto;
import com.kaiqueapr.dxcdesafioapi.models.ClienteModel;
import com.kaiqueapr.dxcdesafioapi.repositories.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

@Autowired
private ClienteRepository clienteRepository;

/*Método responsável por criar um novo Cliente*/
public ClienteResponseDto novoCliente(CriarClienteRequestDto clienteRequest) {
ClienteModel cliente = new ClienteModel();
BeanUtils.copyProperties(clienteRequest, cliente);
cliente = clienteRepository.save(cliente);
return converteParaDto(cliente);
}

/*Método responsável por retornar uma lista de todos os Clientes*/
public Page<ClienteResponseDto> listaClientes(Pageable pageable){
Page<ClienteModel> all = clienteRepository.findAll(pageable);
ModelMapper modelMapper = new ModelMapper();
return new PageImpl<ClienteResponseDto>(all.stream().map(clienteModel -> modelMapper.map(clienteModel, ClienteResponseDto.class)).collect(Collectors.toList()));

}

/*Método responsável por achar um Cliente atráves da variável 'cdCliente'*/
public ClienteResponseDto acharClientePorId(Integer cdCliente){
Optional<ClienteModel> optional = clienteRepository.findById(cdCliente);

if(optional.isEmpty()){
throw new UserNotFound("O usuário que você tentou localizar não existe.");
}
return converteParaDto(optional.get());
}

/*Método responsável por deleter um Cliente atráves da variável 'cdCliente'*/
public void deletarCliente(Integer cdCliente){
if(clienteRepository.existsById(cdCliente)){
clienteRepository.deleteById(cdCliente);
}
throw new UserNotFound("O usuário que você tentou localizar não existe.");
}

/*Método responsável por realizar um update no Cliente atráves da variável 'cdCliente'*/
public ClienteResponseDto updatePorId(CriarClienteRequestDto criarClienteRequestDto, Integer cdCliente){
Optional<ClienteModel> optional = clienteRepository.findById(cdCliente);
if(optional.isPresent()){
ClienteModel cliente = optional.get();
BeanUtils.copyProperties(criarClienteRequestDto, cliente);
cliente = clienteRepository.save(cliente);
return converteParaDto(cliente);
}
throw new UserNotFound("O usuário que você tentou localizar não existe.");
}

/*Método responsável por converter um objeto para DTO*/
private ClienteResponseDto converteParaDto(ClienteModel cliente) {
ClienteResponseDto clienteDto = new ClienteResponseDto();
clienteDto.setCdCliente(cliente.getCdCliente());
clienteDto.setNmCliente(cliente.getNmCliente());
clienteDto.setNmEmail(cliente.getNmEmail());
clienteDto.setNrTelefone(cliente.getNrTelefone());
return clienteDto;
}

}

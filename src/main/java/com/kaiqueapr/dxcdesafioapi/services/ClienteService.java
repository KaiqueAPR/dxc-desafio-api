package com.kaiqueapr.dxcdesafioapi.services;

import com.kaiqueapr.dxcdesafioapi.dtos.ClienteResponseDto;
import com.kaiqueapr.dxcdesafioapi.dtos.CriarClienteRequestDto;
import com.kaiqueapr.dxcdesafioapi.models.ClienteModel;
import com.kaiqueapr.dxcdesafioapi.repositories.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<ClienteResponseDto> listaClientes(){
        List<ClienteModel> all = clienteRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        return all.stream().map(clienteModel -> modelMapper.map(clienteModel, ClienteResponseDto.class)).collect(Collectors.toList());
    }

    /*Método responsável por achar um Cliente atráves da variável 'cdCliente'*/
    public ClienteResponseDto acharClientePorId(Integer cdCliente){
        Optional<ClienteModel> optional = clienteRepository.findById(cdCliente);
        return optional.map(this::converteParaDto).orElse(null);
    }

    /*Método responsável por deleter um Cliente atráves da variável 'cdCliente'*/
    public void deletarCliente(Integer cdCliente){
        if(clienteRepository.existsById(cdCliente)){
            clienteRepository.deleteById(cdCliente);
        }
    }

    /*Método responsável por realizar um update no Cliente atráves da variável 'cdCliente'*/
    public ClienteResponseDto updatePorId(CriarClienteRequestDto criarClienteRequestDto, Integer cdCliente){
        Optional<ClienteModel> optional = clienteRepository.findById(cdCliente);
        if(optional.isPresent()){
            ClienteModel cliente = optional.get();

        if(criarClienteRequestDto.getNmCliente() != null){
            cliente.setNmCliente(criarClienteRequestDto.getNmCliente());
            }
        if(criarClienteRequestDto.getNmEmail() != null){
            cliente.setNmEmail(criarClienteRequestDto.getNmEmail());
            }
        if(criarClienteRequestDto.getNrTelefone() != null){
            cliente.setNrTelefone(criarClienteRequestDto.getNrTelefone());
            }
        clienteRepository.save(cliente);
        return converteParaDto(cliente);
        }
        return null;
    }

    /*Método responsável por converter um objeto para DTO*/
    private ClienteResponseDto converteParaDto(ClienteModel cliente) {
        ClienteResponseDto clienteDto = new ClienteResponseDto();
        clienteDto.setCdCliente(cliente.getCdCliente());
        clienteDto.setNmNome(cliente.getNmCliente());
        clienteDto.setNmEmail(cliente.getNmEmail());
        clienteDto.setNrTelefone(cliente.getNrTelefone());
        return clienteDto;
    }

}

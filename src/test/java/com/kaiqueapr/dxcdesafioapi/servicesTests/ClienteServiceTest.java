package com.kaiqueapr.dxcdesafioapi.servicesTests;

import com.kaiqueapr.dxcdesafioapi.models.ClienteModel;
import com.kaiqueapr.dxcdesafioapi.repositories.ClienteRepository;
import com.kaiqueapr.dxcdesafioapi.services.ClienteService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ClienteServiceTest {

    private static final Integer cdCliente = 1;
    private static final String nmCliente = "Kaique Teste";
    private static final String nmEmail = "kaiqueteste@hotmail.com";
    private static final String nrTelefone = "1399999999";
    private static final String cdSenha = "senhaTeste";

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    /*JUnit teste para validar a criação de um novo Cliente. A verificação ocorre perguntando se o código do Cliente gerado é maior do que 0
     * Teste de validação do método POST
     * */
    @Test
    @Order(1)
    public void novoClienteTest() {

        ClienteModel cliente = criaCliente();

        clienteRepository.save(cliente);

        Assertions.assertThat(cliente.getCdCliente()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    public void acharClientePorIdTest() {

        ClienteModel cliente = clienteRepository.findById(1).get();
        ClienteModel clienteExistente = criaCliente();

        System.out.println("Este é o cliente do teste:" );
        System.out.println(cliente);
        System.out.println("Este é o cliente comparador:" );
        System.out.println(clienteExistente);



        Assertions.assertThat(cliente).isEqualTo(clienteExistente);
    }

    @Test
    @Order(3)
    public void listaClientesTest() {
        List<ClienteModel> listaCliente = clienteRepository.findAll();
        Assertions.assertThat(listaCliente.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void updatePorIdTest() {
        ClienteModel cliente = clienteRepository.findById(1).get();

        cliente.setNmEmail("novoEmail@hotmail.com");

        ClienteModel clienteUpdate = clienteRepository.save(cliente);

        Assertions.assertThat(clienteUpdate.getNmEmail()).isEqualTo("novoEmail@hotmail.com");
    }

    @Test
    @Order(5)
    public void deletarClienteTest() {

        ClienteModel cliente = clienteRepository.findById(1).get();

        clienteRepository.delete(cliente);

        ClienteModel cliente1 = null;

        Optional<ClienteModel> optionalCliente = clienteRepository.findByNmEmail("novoEmail@hotmail.com");

        if (optionalCliente.isPresent()) {
            cliente1 = optionalCliente.get();
        }

        Assertions.assertThat(cliente1).isNull();

    }

    private ClienteModel criaCliente() {

        ClienteModel cliente = ClienteModel.builder()
                .cdCliente(1)
                .nmCliente("Kaique Teste")
                .nmEmail("kaiqueteste@hotmail.com")
                .nrTelefone("1399999999")
                .cdSenha("senhaTeste").build();

        clienteRepository.save(cliente);

        return cliente;

    }
}

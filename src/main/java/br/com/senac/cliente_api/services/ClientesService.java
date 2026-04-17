package br.com.senac.cliente_api.services;

import br.com.senac.cliente_api.dtos.ClienteRequestDTO;
import br.com.senac.cliente_api.entidades.Cliente;
import br.com.senac.cliente_api.repositorios.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesService {

    private ClienteRepository clienteRepository;

    public ClientesService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }
    public Cliente criar(ClienteRequestDTO cliente) {
        Cliente clientePersit = this.clienteRequestDTOParaCliente(cliente);
        return clienteRepository.save(clientePersit);
    }

    public Cliente atualizar(Long id,ClienteRequestDTO cliente) {
        if (clienteRepository.existsById(id)) {
            Cliente clientePersit = this.clienteRequestDTOParaCliente(cliente);
            clientePersit.setId(id);
            return clienteRepository.save(clientePersit);
        }
        throw new RuntimeException("Cliente não encontrado");
    }
    public void deletar(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        }
        throw new RuntimeException("Cliente deletado com sucesso");
    }


    private Cliente clienteRequestDTOParaCliente(
            ClienteRequestDTO entrada){
        Cliente saida = new Cliente();
        saida.setNome(entrada.getNome());
        saida.setEmail(entrada.getEmail());
        saida.setDocumento(entrada.getDocumento());
        saida.setIdade(entrada.getIdade());

        return saida;
    }





}

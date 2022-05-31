package com.example.diodesignpatternsspring.service.impl;

import com.example.diodesignpatternsspring.model.Cliente;
import com.example.diodesignpatternsspring.model.ClienteRepository;
import com.example.diodesignpatternsspring.model.Endereco;
import com.example.diodesignpatternsspring.model.EnderecoRepository;
import com.example.diodesignpatternsspring.service.ClienteService;
import com.example.diodesignpatternsspring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClienteService {

  // Singleton : Injetando com AutoWired
  @Autowired
  private ClienteRepository clienteRepository;
  @Autowired
  private EnderecoRepository enderecoRepository;
  @Autowired
  private ViaCepService viacepService;

  // Strategy : Implementando métodos da interface
  // Facade : Abstraindo integrações com subsistemas
  @Override
  public Iterable<Cliente> buscarTodos(){
    return clienteRepository.findAll();
  }
  @Override
  public Cliente buscarPorId(Long id){
    Optional<Cliente> cliente = clienteRepository.findById(id);
    return cliente.get();
  }

  @Override
  public void inserir(Cliente cliente){
    salvaCliente(cliente);
  }

  private void salvaCliente(Cliente cliente) {
    String cep = cliente.getEndereco().getCep();
    Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
      Endereco novoEndereco = viacepService.consultarCep(cep);
      enderecoRepository.save(novoEndereco);
      return novoEndereco;
    });
    cliente.setEndereco(endereco);
    clienteRepository.save(cliente);
  }

  @Override
  public void atualizar(Long id, Cliente cliente){
    Optional<Cliente> buscaCliente = clienteRepository.findById(id);
    if(buscaCliente.isPresent()){
      salvaCliente(cliente);
    }
  }

  @Override
  public void deletar(Long id){
    clienteRepository.deleteById(id);
  }
}

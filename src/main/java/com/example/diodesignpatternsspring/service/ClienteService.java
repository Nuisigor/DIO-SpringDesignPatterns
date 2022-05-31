package com.example.diodesignpatternsspring.service;


import com.example.diodesignpatternsspring.model.Cliente;

public interface ClienteService {

  Iterable<Cliente> buscarTodos();
  Cliente buscarPorId(Long id);
  void inserir(Cliente cliente);
  void atualizar(Long id, Cliente cliente);
  void deletar(Long id);
}

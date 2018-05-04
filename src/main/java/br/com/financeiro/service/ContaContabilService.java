package br.com.financeiro.service;

import br.com.financeiro.model.ContaContabil;

import java.util.List;

public interface ContaContabilService {

    ContaContabil save(ContaContabil contaContabil);
    ContaContabil find(String id);
    List<ContaContabil> findAll();
    ContaContabil findByNumero(Long numero);
    void deleteById(String id);
}

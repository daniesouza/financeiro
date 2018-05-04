package br.com.financeiro.dao;

import br.com.financeiro.model.ContaContabil;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ContaContabilRepository extends MongoRepository<ContaContabil, String> {

    ContaContabil findByNumero(Long numero);
}

package br.com.financeiro.dao;

import br.com.financeiro.model.ContaContabil;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContaContabilRepository extends MongoRepository<ContaContabil, String> {

    ContaContabil findByNumero(Long numero);
}

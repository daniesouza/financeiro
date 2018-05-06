package br.com.financeiro.dao;

import br.com.financeiro.model.LancamentoContabil;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface LancamentoContabilRepository extends MongoRepository<LancamentoContabil, String> {

    List<LancamentoContabil> findByContaContabil(Long contaContabil);
}

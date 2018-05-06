package br.com.financeiro.service;

import br.com.financeiro.model.EstatisticaLancamentoContabil;
import br.com.financeiro.model.LancamentoContabil;

import java.util.List;

public interface LancamentoContabilService {

    LancamentoContabil save(LancamentoContabil lancamentoContabil);
    LancamentoContabil update(LancamentoContabil lancamentoContabil);
    LancamentoContabil find(String id);
    List<LancamentoContabil> findAll();
    List<LancamentoContabil> findByContaContabil(Long contaContabil);
    void deleteById(String id);
    EstatisticaLancamentoContabil findEstatisticasLancamentos(Long contaContabil);

}

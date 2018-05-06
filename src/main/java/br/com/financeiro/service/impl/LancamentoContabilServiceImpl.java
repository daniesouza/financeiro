package br.com.financeiro.service.impl;

import br.com.financeiro.dao.LancamentoContabilRepository;
import br.com.financeiro.exception.handler.ServiceValidationException;
import br.com.financeiro.model.LancamentoContabil;
import br.com.financeiro.model.EstatisticaLancamentoContabil;
import br.com.financeiro.service.LancamentoContabilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LancamentoContabilServiceImpl implements LancamentoContabilService {

    @Autowired
    private LancamentoContabilRepository lancamentoContabilRepository;


    @Override
    public LancamentoContabil save(LancamentoContabil lancamentoContabil) {

        LancamentoContabil lanc = lancamentoContabilRepository.findByContaContabilAndData(lancamentoContabil.getContaContabil(),lancamentoContabil.getData());

        if(lanc != null){
            throwAlreadyExistException();
        }

        lancamentoContabil.setId(UUID.randomUUID().toString());

        return lancamentoContabilRepository.insert(lancamentoContabil);
    }

    @Override
    public LancamentoContabil update(LancamentoContabil lancamentoContabil) {

        LancamentoContabil lanc = lancamentoContabilRepository.findByContaContabilAndData(lancamentoContabil.getContaContabil(),lancamentoContabil.getData());

        if(lanc != null && !lanc.getId().equals(lancamentoContabil.getId())){
            throwAlreadyExistException();
        }

        return lancamentoContabilRepository.save(lancamentoContabil);
    }

    public ServiceValidationException throwAlreadyExistException(){
        throw new ServiceValidationException("LançamentoContabil already existis with (contaContabil and data).",
                "lanc_exist",
                "lancamentoContabil");
    }

    @Override
    public LancamentoContabil find(String id){
       Optional<LancamentoContabil> lancamentoContabilOpt = lancamentoContabilRepository.findById(id);
       return lancamentoContabilOpt.isPresent() ? lancamentoContabilOpt.get() : null;
    }

    @Override
    public List<LancamentoContabil> findAll() {
        return lancamentoContabilRepository.findAll();
    }

    @Override
    public List<LancamentoContabil> findByContaContabil(Long contaContabil){
        return lancamentoContabilRepository.findByContaContabil(contaContabil);
    }

    @Override
    public EstatisticaLancamentoContabil findEstatisticasLancamentos(Long contaContabil){

        List<LancamentoContabil> lancamentos;

        if(contaContabil == null){
            lancamentos = findAll();
        }else{
            lancamentos = findByContaContabil(contaContabil);
        }

        if(lancamentos == null || lancamentos.isEmpty()){
            return new EstatisticaLancamentoContabil(BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO);
        }

        lancamentos.stream().mapToDouble(value -> value.getValor().doubleValue()).sum();

        BigDecimal soma = lancamentos.stream()
                .map(LancamentoContabil::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal min = lancamentos.stream().min(Comparator.comparing(LancamentoContabil::getValor)).get().getValor();
        BigDecimal max = lancamentos.stream().max(Comparator.comparing(LancamentoContabil::getValor)).get().getValor();
        BigDecimal qtde = BigDecimal.valueOf(lancamentos.size());
        BigDecimal media = soma.divide(qtde,BigDecimal.ROUND_HALF_UP);

        return new EstatisticaLancamentoContabil(soma,min,max,media,qtde);

    }

    @Override
    public void deleteById(String id){
        lancamentoContabilRepository.deleteById(id);
    }
}

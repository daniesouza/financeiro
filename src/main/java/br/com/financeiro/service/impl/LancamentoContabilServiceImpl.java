package br.com.financeiro.service.impl;

import br.com.financeiro.dao.LancamentoContabilRepository;
import br.com.financeiro.exception.handler.ServiceValidationException;
import br.com.financeiro.model.EstatisticaLancamentoContabil;
import br.com.financeiro.model.LancamentoContabil;
import br.com.financeiro.model.dto.LancamentoContabilDTO;
import br.com.financeiro.service.LancamentoContabilService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LancamentoContabilServiceImpl implements LancamentoContabilService {

    /**
     * Default Logger.
     *
     * @see LogManager
     */
    private static final Logger logger = LogManager.getLogger(LancamentoContabilServiceImpl.class);

    @Autowired
    private LancamentoContabilRepository lancamentoContabilRepository;


    /**
     *
     * Could have used a synchronized method but i preferred to let database handle concurrency
     *
     * @param lancamentoContabil
     * @return LancamentoContabil
     * @see LancamentoContabil
     */
    @Override
    public LancamentoContabil save(LancamentoContabil lancamentoContabil) {

        LancamentoContabil lanc = lancamentoContabilRepository.findByContaContabilAndData(lancamentoContabil.getContaContabil(),lancamentoContabil.getData());

        if(lanc != null){
            ServiceValidationException sve = getAlreadyExistException();
            throw sve;
        }

        lancamentoContabil.setId(UUID.randomUUID().toString());

        /*
         * if throw DuplicateKeyException that means a concurrency issue to be treated.
         */
        try{
            return lancamentoContabilRepository.insert(lancamentoContabil);
        }catch (DuplicateKeyException ex ){
            logger.error(ex);
            ServiceValidationException sve = getAlreadyExistException();
            throw sve;
        }
    }

    /**
     *
     *  Could have used a synchronized method but i preferred to let database handle concurrency
     *
     * @param lancamentoContabil
     * @return LancamentoContabil
     * @see LancamentoContabil
     */
    @Override
    public LancamentoContabil update(LancamentoContabil lancamentoContabil) {

        LancamentoContabil lanc = lancamentoContabilRepository.findByContaContabilAndData(lancamentoContabil.getContaContabil(),lancamentoContabil.getData());

        if(lanc != null && !lanc.getId().equals(lancamentoContabil.getId())){
            ServiceValidationException sve = getAlreadyExistException();
            throw sve;
        }

        /*
         * if throw DuplicateKeyException that means a concurrency issue to be treated.
         */
        try{
            return lancamentoContabilRepository.save(lancamentoContabil);
        }catch (DuplicateKeyException ex ){
            logger.error(ex);
            ServiceValidationException sve = getAlreadyExistException();
            throw sve;
        }
    }

    private ServiceValidationException getAlreadyExistException(){
        return new ServiceValidationException("LançamentoContabil already existis with (contaContabil and data).",
                "lanc_exist",
                "lancamentoContabil");
    }

    /**
     * @param id
     * @return LancamentoContabil
     * @see LancamentoContabil
     */
    @Override
    public LancamentoContabil find(String id){
       Optional<LancamentoContabil> lancamentoContabilOpt = lancamentoContabilRepository.findById(id);
       return lancamentoContabilOpt.isPresent() ? lancamentoContabilOpt.get() : null;
    }

    /**
     * @return List<LancamentoContabil>
     * @see LancamentoContabil
     */
    @Override
    public List<LancamentoContabil> findAll() {
        return lancamentoContabilRepository.findAll();
    }

    /**
     * @param contaContabil
     * @return List<LancamentoContabil>
     * @see LancamentoContabil
     */
    @Override
    public List<LancamentoContabil> findByContaContabil(Long contaContabil){
        return lancamentoContabilRepository.findByContaContabil(contaContabil);
    }

    /**
     * @param contaContabil
     * @return EstatisticaLancamentoContabil
     * @see EstatisticaLancamentoContabil
     */
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


        BigDecimal soma = lancamentos.stream()
                .map(LancamentoContabil::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal min = lancamentos.stream().min(Comparator.comparing(LancamentoContabil::getValor)).get().getValor();
        BigDecimal max = lancamentos.stream().max(Comparator.comparing(LancamentoContabil::getValor)).get().getValor();
        BigDecimal qtde = BigDecimal.valueOf(lancamentos.size());
        BigDecimal media = soma.divide(qtde,BigDecimal.ROUND_HALF_UP);

        return new EstatisticaLancamentoContabil(soma,min,max,media,qtde);

    }

    /**
     * @param id
     */
    @Override
    public void deleteById(String id){
        lancamentoContabilRepository.deleteById(id);
    }
}

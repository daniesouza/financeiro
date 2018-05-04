package br.com.financeiro.service.impl;

import br.com.financeiro.dao.ContaContabilRepository;
import br.com.financeiro.model.ContaContabil;
import br.com.financeiro.service.ContaContabilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaContabilServiceImpl implements ContaContabilService {

    @Autowired
    private ContaContabilRepository contaContabilRepository;

    @Override
    public ContaContabil save(ContaContabil contaContabil) {
        return contaContabilRepository.save(contaContabil);
    }

    @Override
    public ContaContabil find(String id){
       Optional<ContaContabil> contaContabilOpt = contaContabilRepository.findById(id);
       return contaContabilOpt.isPresent() ? contaContabilOpt.get() : null;
    }

    @Override
    public List<ContaContabil> findAll() {
        return contaContabilRepository.findAll();
    }

    @Override
    public ContaContabil findByNumero(Long numero){
        return contaContabilRepository.findByNumero(numero);
    }

    @Override
    public void deleteById(String id){
        contaContabilRepository.deleteById(id);
    }
}

package br.com.financeiro.controller;

import br.com.financeiro.config.BeanMapper;
import br.com.financeiro.model.EstatisticaLancamentoContabil;
import br.com.financeiro.model.LancamentoContabil;
import br.com.financeiro.model.dto.IdDTO;
import br.com.financeiro.model.dto.LancamentoContabilDTO;
import br.com.financeiro.model.dto.MessageDTO;
import br.com.financeiro.service.LancamentoContabilService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lancamento-contabil")
public class LancamentoContabilController {

    /**
     * Default Logger.
     *
     * @see LogManager
     */
    private static final Logger logger = LogManager.getLogger(LancamentoContabilController.class);

    @Autowired
    private LancamentoContabilService lancamentoContabilService;


    /**
     * Metodo responsavel por receber POST
     *  do objeto LancamentoContabilDTO.
     *
     *            - Objeto LancamentoContabilDTO.
     * @return List<LancamentoContabilDTO>.
     * @see LancamentoContabilDTO
     */
    @GetMapping("/")
    public ResponseEntity<List<LancamentoContabilDTO>> findAll() {

        List<LancamentoContabilDTO> dtos = new ArrayList<>();

        lancamentoContabilService.findAll()
                .forEach(cc -> {
                    dtos.add(BeanMapper.getInstance().map(cc,LancamentoContabilDTO.class));
                });

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancamentoContabilDTO> find(@PathVariable("id") String id) {

        LancamentoContabil lancamentoContabil = lancamentoContabilService.find(id);

        if (lancamentoContabil == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(BeanMapper.getInstance().map(lancamentoContabil,LancamentoContabilDTO.class), HttpStatus.OK);
    }

    @GetMapping("/contaContabil/{contaContabil}")
    public ResponseEntity<List<LancamentoContabilDTO>> findByNumero(@PathVariable("contaContabil") Long contaContabil) {

        List<LancamentoContabilDTO> dtos = new ArrayList<>();

        lancamentoContabilService.findByContaContabil(contaContabil)
                .forEach(cc -> {
                    dtos.add(BeanMapper.getInstance().map(cc,LancamentoContabilDTO.class));
                });

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<IdDTO> save(@RequestBody @Valid final LancamentoContabilDTO lancamentoContabilDTO) {

        logger.info("Saving "+lancamentoContabilDTO.toString());

        LancamentoContabil lancamentoContabil = lancamentoContabilService.save(BeanMapper.getInstance().map(lancamentoContabilDTO,LancamentoContabil.class));

        return new ResponseEntity<>(new IdDTO(lancamentoContabil.getId()), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IdDTO> update(@PathVariable("id") String id, @RequestBody @Valid final LancamentoContabilDTO lancamentoContabilDTO) {

        logger.info("Updating "+lancamentoContabilDTO.toString());

        LancamentoContabil lancamentoContabil = lancamentoContabilService.find(id);
        if (lancamentoContabil == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        lancamentoContabil.setContaContabil(lancamentoContabilDTO.getContaContabil());
        lancamentoContabil.setData(lancamentoContabilDTO.getData());
        lancamentoContabil.setValor(lancamentoContabilDTO.getValor());

        lancamentoContabilService.update(lancamentoContabil);

        return new ResponseEntity<>(new IdDTO(lancamentoContabil.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> delete(@PathVariable("id") String id) {
        logger.info("Deleting Lançamento with ID = " + id + "...");
        lancamentoContabilService.deleteById(id);
        return new ResponseEntity<>(new MessageDTO("Lancamento has been deleted!"), HttpStatus.OK);
    }


    @GetMapping("/_stats/")
    public ResponseEntity<EstatisticaLancamentoContabil> find(@RequestParam(value = "contaContabil", required = false) Long contaContabil) {
        EstatisticaLancamentoContabil estatistica = lancamentoContabilService.findEstatisticasLancamentos(contaContabil);
        return new ResponseEntity<>(estatistica, HttpStatus.OK);
    }
}

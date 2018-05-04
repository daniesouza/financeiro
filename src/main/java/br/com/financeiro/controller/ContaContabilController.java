package br.com.financeiro.controller;

import br.com.financeiro.model.ContaContabil;
import br.com.financeiro.model.dto.ContaContabilDTO;
import br.com.financeiro.service.ContaContabilService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/conta-contabil")
public class ContaContabilController {

    /**
     * Default Logger.
     *
     * @see LogManager
     */
    private static final Logger logger = LogManager.getLogger(ContaContabilController.class);

    @Autowired
    private ContaContabilService contaContabilService;


    /**
     * Metodo responsavel por receber POST
     *  do objeto ContaContabilDTO.
     *
     *            - Objeto ContaContabilDTO.
     * @return List<ContaContabil>.
     * @see ContaContabilDTO
     */
    @GetMapping("/")
    public ResponseEntity<List<ContaContabil>> findAll() {
        return new ResponseEntity<>(contaContabilService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaContabil> find(@PathVariable("id") String id) {
        return new ResponseEntity<>(contaContabilService.find(id), HttpStatus.OK);
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<ContaContabil> findByNumero(@PathVariable("numero") Long numero) {

        ContaContabil contaContabil = contaContabilService.findByNumero(numero);

        if (contaContabil == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(contaContabil, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ContaContabil> saveContaContabil(@RequestBody @Valid final ContaContabilDTO contaContabilDTO) {

        logger.info("Saving "+contaContabilDTO.toString());

        ContaContabil contaContabil = contaContabilService.save(new ContaContabil(contaContabilDTO.getNumero(),contaContabilDTO.getDescricao()));

        return new ResponseEntity<>(contaContabil, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaContabil> update(@PathVariable("id") String id, @RequestBody @Valid final ContaContabilDTO contaContabilDTO) {

        logger.info("Updating "+contaContabilDTO.toString());

        ContaContabil contaContabil = contaContabilService.find(id);
        if (contaContabil == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        contaContabil.setNumero(contaContabilDTO.getNumero());
        contaContabil.setDescricao(contaContabilDTO.getDescricao());
        contaContabil = contaContabilService.save(contaContabil);

        return new ResponseEntity<>(contaContabil, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        logger.info("Deleting Conta with ID = " + id + "...");
        contaContabilService.deleteById(id);
        return new ResponseEntity<>("ContaContabil has been deleted!", HttpStatus.OK);
    }
}

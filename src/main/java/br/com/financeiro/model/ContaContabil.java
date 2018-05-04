package br.com.financeiro.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;

@Document(collection = "conta-contabil")
//@CompoundIndexes({@CompoundIndex(name = "unique_lancamento",unique = true,def = "{'numero' : 1, 'descricao' : 1}")})
public class ContaContabil extends BaseEntity<String> implements Serializable {

    private static final long serialVersionUID = -5936176657624534961L;

    @Indexed(unique = true)
    private Long numero;

    private String descricao;

    public ContaContabil() {
        super();
    }

    public ContaContabil(Long numero, String descricao) {
        this.id = UUID.randomUUID().toString();
        this.numero = numero;
        this.descricao = descricao;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

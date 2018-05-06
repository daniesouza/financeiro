package br.com.financeiro.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Document(collection = "lancamentos-contabeis")
@CompoundIndexes({@CompoundIndex(name = "unique_lancamento",unique = true, def = "{'data' : 1, 'contaContabil': 1 }")})
public class LancamentoContabil implements Serializable {


    private static final long serialVersionUID = -5452972941608457365L;

    @Id
    private String id;
    private Long contaContabil;
    private BigDecimal valor;
    private Date data;

    public LancamentoContabil() {
        super();
    }

    public LancamentoContabil(Long contaContabil,BigDecimal valor, Date data) {
        this.contaContabil = contaContabil;
        this.valor = valor;
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getContaContabil() {
        return contaContabil;
    }

    public void setContaContabil(Long contaContabil) {
        this.contaContabil = contaContabil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LancamentoContabil that = (LancamentoContabil) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(contaContabil, that.contaContabil) &&
                Objects.equals(valor, that.valor) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contaContabil, valor, data);
    }
}

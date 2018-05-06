package br.com.financeiro.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LancamentoContabilDTO implements Serializable{

    private static final long serialVersionUID = 1756067942623196941L;

    private String id;

    @NotNull(message = "Numero da conta não pode ser nulo!")
    private Long contaContabil;

    @NotNull(message = "Data do lançamento não pode ser nulo!")
    private Date data;

    @NotNull(message = "Valor do lançamento não pode ser nulo!")
    private BigDecimal valor;

    public LancamentoContabilDTO() {
        super();
    }


    public Long getContaContabil() {
        return contaContabil;
    }

    public void setContaContabil(Long contaContabil) {
        this.contaContabil = contaContabil;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

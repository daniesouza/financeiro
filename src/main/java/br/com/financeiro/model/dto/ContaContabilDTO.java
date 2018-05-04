package br.com.financeiro.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ContaContabilDTO implements Serializable{

    private static final long serialVersionUID = 1756067942623196941L;

    @NotNull(message = "Numero da conta não pode ser nulo!")
    private Long numero;

    @NotNull(message = "Descrição da conta não pode ser nulo!")
    @Size(min = 1,message = "Descrição da conta é obrigatório!")
    private String descricao;

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

    @Override
    public String toString() {
        return "ContaContabilDTO{" +
                "numero=" + numero +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}

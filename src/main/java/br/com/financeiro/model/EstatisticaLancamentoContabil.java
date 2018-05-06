package br.com.financeiro.model;

import java.math.BigDecimal;

public class EstatisticaLancamentoContabil {

    private BigDecimal soma;
    private BigDecimal min;
    private BigDecimal max;
    private BigDecimal media;
    private BigDecimal quantidade;

    public EstatisticaLancamentoContabil(BigDecimal soma, BigDecimal min, BigDecimal max, BigDecimal media, BigDecimal quantidade) {
        this.soma = soma;
        this.min = min;
        this.max = max;
        this.media = media;
        this.quantidade = quantidade;
    }

    public BigDecimal getSoma() {
        return soma;
    }

    public void setSoma(BigDecimal soma) {
        this.soma = soma;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getMedia() {
        return media;
    }

    public void setMedia(BigDecimal media) {
        this.media = media;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }
}

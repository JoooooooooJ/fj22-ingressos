package br.com.caelum.ingresso.model;

import br.com.caelum.ingresso.model.descontos.Desconto;

import java.math.BigDecimal;

public class Ingresso {

    private Sessao sessao;
    private BigDecimal preco;

    /**
     * @deprecated hibernate only
     */
    public Ingresso(){

    }

    public Ingresso(Sessao sessao, Desconto tipodeDesconto) {
        this.sessao = sessao;
        this.preco = tipodeDesconto.aplicardescontoSobre(sessao.getPreco());
    }

    public Sessao getSessao() {
        return sessao;
    }

    public BigDecimal getPreco() {
        return preco;
    }
}

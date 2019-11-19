package br.com.caelum.ingresso.model;

import br.com.caelum.ingresso.model.descontos.Desconto;

import javax.persistence.*;
import java.math.BigDecimal;

public class Ingresso {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Sessao sessao;

    private BigDecimal preco;

    @ManyToOne
    private Lugar lugar;

    @Enumerated(EnumType.STRING)
    private TipoDeIngresso tipoDeIngresso;

    /**
     * @deprecated hibernate only
     */
    public Ingresso(){

    }

    public Ingresso(Sessao sessao, TipoDeIngresso tipodeDesconto, Lugar lugar) {
        this.sessao = sessao;
        this.tipoDeIngresso = tipodeDesconto;
        this.preco = tipodeDesconto.aplicaDesconto(sessao.getPreco());
        this.lugar = lugar;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getId() {
        return id;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public TipoDeIngresso getTipoDeIngresso() {
        return tipoDeIngresso;
    }
}

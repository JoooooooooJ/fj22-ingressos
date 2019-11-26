package br.com.caelum.ingresso.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingresso {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Sessao sessao;

    @ManyToOne
    private Lugar lugar;

    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private TipoDeIngresso tipoDeIngresso;

    /**
     * @deprecated hibernate only*/
    public Ingresso(){

    }

    public Ingresso(Sessao sessao, TipoDeIngresso tipoDeIngresso, Lugar lugar) {
        this.sessao = sessao;
        this.tipoDeIngresso = tipoDeIngresso;
        this.preco = this.tipoDeIngresso.aplicaDesconto(sessao.getPreco());
        this.lugar = lugar;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public BigDecimal getPrecoComDesconto(){
        return tipoDeIngresso.aplicaDesconto(preco);
    }

    public BigDecimal getValorDeDesconto(){

        BigDecimal desconto = getPrecoComDesconto();

        return preco.subtract(desconto);
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public TipoDeIngresso getTipoDeIngresso() {
        return tipoDeIngresso;
    }

    public void setTipoDeIngresso(TipoDeIngresso tipoDeIngresso) {
        this.tipoDeIngresso = tipoDeIngresso;
    }
}

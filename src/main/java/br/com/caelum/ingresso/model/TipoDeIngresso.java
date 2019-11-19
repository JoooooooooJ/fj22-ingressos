package br.com.caelum.ingresso.model;

import br.com.caelum.ingresso.model.descontos.Desconto;
import br.com.caelum.ingresso.model.descontos.DescontoBanco;
import br.com.caelum.ingresso.model.descontos.DescontoEstudante;
import br.com.caelum.ingresso.model.descontos.SemDesconto;

import java.math.BigDecimal;

public enum  TipoDeIngresso {

    INTEIRO(new SemDesconto()),
    ESTUDANTE(new DescontoEstudante()),
    BANCO(new DescontoBanco());

    private final Desconto desconto;

    TipoDeIngresso(Desconto desconto){
        this.desconto = desconto;
    }

    public BigDecimal aplicaDesconto(BigDecimal valor){
        return desconto.aplicardescontoSobre(valor);
    }

    public String getDescricao(){
        return desconto.getDescricao();
    }
}

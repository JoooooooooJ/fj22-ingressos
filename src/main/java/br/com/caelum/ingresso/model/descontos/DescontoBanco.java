package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public class DescontoBanco implements Desconto{

    @Override
    public BigDecimal aplicardescontoSobre(BigDecimal precoOriginal) {
        return precoOriginal.subtract(trindaPorCentro(precoOriginal));
    }

    @Override
    public String getDescricao() {
        return "Desconto Banco";
    }

    public BigDecimal trindaPorCentro(BigDecimal precoOriginal){
        return precoOriginal.multiply(new BigDecimal("0.3"));
    }
}

package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public class SemDesconto implements Desconto {
    @Override
    public BigDecimal aplicardescontoSobre(BigDecimal precoOriginal) {
        return precoOriginal;
    }
}

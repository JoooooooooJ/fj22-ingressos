package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public class DescontoEstudante implements Desconto {
    @Override
    public BigDecimal aplicardescontoSobre(BigDecimal precoOriginal) {
        return precoOriginal.divide( new BigDecimal(2.0));
    }

    @Override
    public String getDescricao() {
        return "Estudante";
    }
}

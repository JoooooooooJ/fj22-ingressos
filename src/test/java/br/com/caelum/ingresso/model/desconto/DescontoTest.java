package br.com.caelum.ingresso.model.desconto;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.descontos.DescontoBanco;
import br.com.caelum.ingresso.model.descontos.DescontoEstudante;
import br.com.caelum.ingresso.model.descontos.SemDesconto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class DescontoTest {

    @Test
    public void testeDescontoBanco(){

        Sala sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
        Filme filme = new Filme("Rogue One", Duration.ofMinutes(120),
                "SCI-FI", new BigDecimal("12"));
        Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
        Ingresso ingresso = new Ingresso(sessao, new DescontoBanco());

        BigDecimal precoEsperado = new BigDecimal("22.75");

        Assert.assertEquals(precoEsperado, ingresso.getPreco());
    }

    @Test
    public void testeDescontoEstudante(){

        Sala sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
        Filme filme = new Filme("Rogue One", Duration.ofMinutes(120),
                "SCI-FI", new BigDecimal("12"));
        Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
        Ingresso ingresso = new Ingresso(sessao, new DescontoEstudante());

        BigDecimal precoEsperado = new BigDecimal("16.25");

        Assert.assertEquals(precoEsperado, ingresso.getPreco());
    }

    @Test

    public void testeValorSemDesconto(){

        Sala sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
        Filme filme = new Filme("Rogue One", Duration.ofMinutes(120),
                "SCI-FI", new BigDecimal("12"));
        Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), sala, filme);
        Ingresso ingresso = new Ingresso(sessao, new SemDesconto());

        BigDecimal precoEsperado = new BigDecimal("32.5");

        Assert.assertEquals(precoEsperado, ingresso.getPreco());
    }
}

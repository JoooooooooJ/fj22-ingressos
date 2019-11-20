package br.com.caelum.ingresso;

import br.com.caelum.ingresso.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SessaoTest {

    @Test
    public void garanteQueOLugarA1EstaOcupadoEOsLugaresA2EA3Disponiveis(){

        Lugar a1 = new Lugar("A", 1);
        Lugar a2 = new Lugar("A", 2);
        Lugar a3 = new Lugar("A", 3);

        Filme rogueOne = new Filme("Rogue One", Duration.ofMinutes(120),
                "SCI_FI", new BigDecimal("12.0"));

        Sala eldorado7 = new Sala("Eldorado 7", new BigDecimal("8.5"));

        Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), eldorado7, rogueOne);

        Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRO, a1);

        Set<Ingresso> ingressos = Stream.of(ingresso).collect(Collectors.toSet());

        sessao.setIngressos(ingressos);

        Assert.assertFalse(sessao.isDisponivel(a1));
        Assert.assertTrue(sessao.isDisponivel(a2));
        Assert.assertTrue(sessao.isDisponivel(a3));
    }

}

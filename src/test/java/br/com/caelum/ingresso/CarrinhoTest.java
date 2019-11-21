package br.com.caelum.ingresso;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.*;
import br.com.caelum.ingresso.model.form.CarrinhoForm;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarrinhoTest {

    @Test
    public void CarrinhoTest(){
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

        Carrinho carrinho = new Carrinho();
        carrinho.add(ingresso);

        CarrinhoForm carrinhoForm = new CarrinhoForm();
        carrinhoForm.toIngressos(new SessaoDao(), new LugarDao()).forEach(carrinho::add);

    }
}

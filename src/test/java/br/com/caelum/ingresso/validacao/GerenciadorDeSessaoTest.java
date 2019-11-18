package br.com.caelum.ingresso.validacao;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;

public class GerenciadorDeSessaoTest {
	
	@Test
	public void garanteQueNaoHaveraSessoesNoMesmoHorario() {
			Filme filme = new Filme("Rougue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal(22.00));
			LocalTime horario = LocalTime.parse("10:00:00");

			Sala sala = new Sala("", new BigDecimal(22.00) );

			Sessao sessao  = new Sessao(horario, sala, filme);

			List<Sessao> sessoes = Arrays.asList(sessao);

		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);

		Assert.assertFalse(gerenciador.cabe(sessao));
	}

	@Test
	public void garanteQueNaoHaveraSessoesTerminandoNoMesmoHorario(){
		Filme filme = new Filme("Rougue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal(22.00));
		LocalTime horario = LocalTime.parse("10:00:00");

		Sala sala = new Sala("",new BigDecimal(22.00));

		Sessao sessao  = new Sessao(horario.minusHours(1), sala, filme);

		List<Sessao> sessoes = Arrays.asList(sessao);

		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);

		Assert.assertFalse(gerenciador.cabe(sessao));
	}

	@Test
	public void garanteQueNaoHaveraSessoesComecandoNoMesmoHorario(){
		Filme filme = new Filme("Rougue One", Duration.ofMinutes(120), "SCI-FI",new BigDecimal(22.00));
		LocalTime horario = LocalTime.parse("10:00:00");

		Sala sala = new Sala("",new BigDecimal(22.00));

		Sessao sessao  = new Sessao(horario.plusHours(1), sala, filme);

		List<Sessao> sessoes = Arrays.asList(sessao);

		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);

		Assert.assertFalse(gerenciador.cabe(sessao));
	}

	@Test
	public void garanteQueUmaSessaoPodeSerInseridaEntreDoisFilmes(){

		Sala sala = new Sala("",new BigDecimal(22.00));

		Filme filme1 = new Filme("Rogue One", Duration.ofMinutes(90), "SCI-FI",new BigDecimal(22.00));
		LocalTime dezHoras = LocalTime.parse("10:00:00");
		Sessao sessaoDasDez = new Sessao(dezHoras, sala, filme1);

		Filme filme2 = new Filme("Rogue One", Duration.ofMinutes(120),"SCI-FI",new BigDecimal(22.00));
		LocalTime dezoitoHoras = LocalTime.parse("18:00:00");
		Sessao sessaoDasDezoito = new Sessao(dezoitoHoras, sala,filme2);

		List<Sessao> sessoes = Arrays.asList(sessaoDasDez, sessaoDasDezoito);

		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);

		Sessao sessao = new Sessao(LocalTime.parse("13:00:00"), sala, filme2);

		Assert.assertTrue(gerenciador.cabe(sessao));

	}


}

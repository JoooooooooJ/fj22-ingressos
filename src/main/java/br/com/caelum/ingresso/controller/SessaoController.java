package br.com.caelum.ingresso.controller;

import javax.validation.Valid;

import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.ImagemCapa;
import br.com.caelum.ingresso.model.TipoDeIngresso;
import br.com.caelum.ingresso.rest.ImdbClient;
import br.com.caelum.ingresso.validacao.GerenciadorDeSessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;

import java.util.List;
import java.util.Optional;

@Controller
public class SessaoController {
	
	@Autowired
	private SalaDao salaDao;
	
	@Autowired
	private FilmeDao filmeDao;
	
	@Autowired
	private SessaoDao sessaoDao;

	@Autowired
	private ImdbClient client;

	@Autowired
	private Carrinho carrinho;
	
	@GetMapping("/admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer salaId, SessaoForm form) {
		
		form.setSalaId(salaId);
		
		ModelAndView mnv =  new ModelAndView("sessao/sessao");
		
		mnv.addObject("sala", salaDao.findOne(salaId));
		mnv.addObject("filmes", filmeDao.findAll());
		mnv.addObject("form", form);
		
		return mnv;
	}
	
	@PostMapping(value = "admin/sessao")
	@Transactional
	public ModelAndView salva(@Valid SessaoForm form, BindingResult result) {
		
		if(result.hasErrors()) return form(form.getSalaId(), form);
		
		ModelAndView mnv;
		Sessao sessao = form.toSessao(salaDao, filmeDao);

		List<Sessao> sessoes = sessaoDao.buscaSessoesDaSala(sessao.getSala());

		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		if(gerenciador.cabe(sessao)){
			sessaoDao.save(sessao);
			return new ModelAndView("redirect:/admin/sala/"+form.getSalaId()+"/sessoes");
		}
		return form(form.getSalaId(), form);
	}

	@GetMapping(value="/sessao/{id}/lugares")
	public ModelAndView lugaresNaSessao(@PathVariable("id") Integer sessaoId){
		ModelAndView mnv = new ModelAndView("sessao/lugares");

		Sessao sessao = sessaoDao.findOne(sessaoId);
		Optional<ImagemCapa> capa = client.request(sessao.getFilme(), ImagemCapa.class);

		mnv.addObject("sessao",sessao);
		mnv.addObject("carrinho", carrinho);
		mnv.addObject("imagemCapa", capa.orElse(new ImagemCapa()));
		mnv.addObject("tiposDeIngresso", TipoDeIngresso.values());
		return mnv;
	}

	@DeleteMapping("/admin/sessao/{id}")
	@ResponseBody
	@Transactional
	public void delete(@PathVariable("id") Integer id){
		sessaoDao.delete(id);
	}
}

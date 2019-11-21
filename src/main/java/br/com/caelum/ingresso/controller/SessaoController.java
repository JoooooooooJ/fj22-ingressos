package br.com.caelum.ingresso.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.ImagemCapa;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.rest.ImdbClient;
import br.com.caelum.ingresso.validacao.GerenciadorDeSessao;



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
	private	Carrinho carrinho;


	@GetMapping ("admin/sessao")
	public ModelAndView form (@RequestParam("salaId") Integer salaId, SessaoForm form){

		form.setSalaId(salaId);

		ModelAndView modelAndView = new ModelAndView("sessao/sessao");

		modelAndView.addObject("sala", salaDao.findOne(salaId));
		modelAndView.addObject("filmes", filmeDao.findAll());
		modelAndView.addObject("form", form);
		return modelAndView;

	}

	@PostMapping(value = "/admin/sessao")
	@Transactional
	public ModelAndView salva(@Valid SessaoForm form, BindingResult result) {

		if (result.hasErrors()) return form(form.getSalaId(),form);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/sala/"+form.getSalaId()+"/sessoes");
		Sessao sessao = form.toSessao(salaDao, filmeDao);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessaoDao.buscaSessoesDaSala(salaDao.findOne(form.getSalaId())));
		if(gerenciador.cabe(sessao)){
			sessaoDao.save(sessao);
		}
		return modelAndView;
	}

	@GetMapping("/sessao/{id}/lugares")
	public ModelAndView lugaresNaSessao(@PathVariable("id") Integer sessaoId){

		ModelAndView modelAndView = new ModelAndView("sessao/lugares");

		Sessao sessao = sessaoDao.findOne(sessaoId);

		Optional<ImagemCapa> imagemCapa	= client.request(sessao.getFilme(), ImagemCapa.class);

		modelAndView.addObject("sessao", sessao);
		modelAndView.addObject("carrinho", carrinho);
		modelAndView.addObject("imagemCapa", imagemCapa.orElse(new	ImagemCapa()));
		modelAndView.addObject("tiposDeIngressos", TipoDeIngresso.values());

		return modelAndView;
	}
	@DeleteMapping("/admin/sessao/{id}")
	@ResponseBody
	@Transactional
	public void delete(@PathVariable("id") Integer id){
		sessaoDao.delete(id);
	}
}
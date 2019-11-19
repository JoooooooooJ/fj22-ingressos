package br.com.caelum.ingresso.controller;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.DetalhesDoFilme;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.rest.ImdbClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by nando on 03/03/17.
 */
@Controller
public class FilmeController {


    @Autowired
    private FilmeDao filmeDao;

    @Autowired
    private SessaoDao sessaoDao;

    @Autowired
    private ImdbClient client;


    @GetMapping({"/admin/filme", "/admin/filme/{id}"})
    public ModelAndView form(@PathVariable("id") Optional<Integer> id, Filme filme){

        ModelAndView modelAndView = new ModelAndView("filme/filme");

        if (id.isPresent()){
            filme = filmeDao.findOne(id.get());
        }

        modelAndView.addObject("filme", filme);

        return modelAndView;
    }


    @PostMapping("/admin/filme")
    @Transactional
    public ModelAndView salva(@Valid Filme filme, BindingResult result){

        if (result.hasErrors()) {
            return form(Optional.ofNullable(filme.getId()), filme);
        }

        filmeDao.save(filme);

        ModelAndView view = new ModelAndView("redirect:/admin/filmes");

        return view;
    }


    @GetMapping(value="/admin/filmes")
    public ModelAndView lista(){

        ModelAndView modelAndView = new ModelAndView("filme/lista");

        modelAndView.addObject("filmes", filmeDao.findAll());

        return modelAndView;
    }

    @GetMapping(value="/filme/em-cartaz")
    public ModelAndView emCartaz(){
        ModelAndView mnv = new ModelAndView("filme/em-cartaz");

        mnv.addObject("filmes",filmeDao.findAll());

        return mnv;

    }

    @GetMapping(value="/filme/{id}/detalhe")
    public ModelAndView detalhes(@PathVariable("id") Integer id){
        ModelAndView mnv = new ModelAndView("/filme/detalhe");

        Filme filme = filmeDao.findOne(id);
        List<Sessao> sessoes = sessaoDao.buscaSessoesDoFilme(filme);

        Optional<DetalhesDoFilme> detalhes = client.request(filme);

        mnv.addObject("sessoes", sessoes);
        mnv.addObject("detalhes", detalhes.orElse(new DetalhesDoFilme()));

        return mnv;
    }


    @DeleteMapping("/admin/filme/{id}")
    @ResponseBody
    @Transactional
    public void delete(@PathVariable("id") Integer id){
        filmeDao.delete(id);
    }

}

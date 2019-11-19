package br.com.caelum.ingresso.controller;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.form.CarrinhoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CompraController {
    @Autowired
    private SessaoDao sessaoDao;

    @Autowired
    private LugarDao lugarDao;

    @Autowired
    private Carrinho carrinho;

    @PostMapping("/compra/ignressos")
    public ModelAndView enviarPagamento(CarrinhoForm carrinhoForm){
        ModelAndView mnv  = new ModelAndView("redirect: /compra");

        carrinhoForm.toIngressos(sessaoDao, lugarDao).forEach(carrinho::add);
        return mnv;
    }
}

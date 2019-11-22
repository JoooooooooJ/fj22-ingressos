package br.com.caelum.ingresso.controller;

import br.com.caelum.ingresso.dao.CompraDao;
import br.com.caelum.ingresso.model.Cartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.form.CarrinhoForm;

import javax.validation.Valid;

@Controller
public class CompraController {

    private final SessaoDao sessaoDao;

    private final LugarDao lugarDao;

    private final Carrinho carrinho;

    private final CompraDao compraDao;

    public CompraController(SessaoDao sessaoDao, LugarDao lugarDao, Carrinho carrinho, CompraDao compraDao){
        this.sessaoDao = sessaoDao;
        this.lugarDao = lugarDao;
        this.carrinho = carrinho;
        this.compraDao = compraDao;
    }

    @PostMapping("/compra/ingressos")
    public ModelAndView enviarParaPagamento(CarrinhoForm carrinhoForm){

        ModelAndView modelAndView = new	ModelAndView("redirect:/compra");

        carrinhoForm.toIngressos(sessaoDao, lugarDao).forEach(carrinho::add);

        return modelAndView;
    }

    @GetMapping("/compra")
    public ModelAndView  checkout(Cartao cartao){

        ModelAndView modelAndView = new	ModelAndView("compra/pagamento");

        modelAndView.addObject("carrinho", carrinho);

        return	modelAndView;
    }

    @PostMapping("/compra/comprar")
    @Transactional
    public ModelAndView comprar(@Valid Cartao cartao, BindingResult result){
        ModelAndView mnv = new ModelAndView("redirect:/");

        if(cartao.isValido()){
            compraDao.save(carrinho.toCompra());
        }
        else{
            result.rejectValue("vencimento", "Vencimento inválido ");
            return checkout(cartao);
        }
        return mnv;
    }
}
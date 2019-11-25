package br.com.caelum.ingresso.controller;

import br.com.caelum.ingresso.dao.UsuarioDao;
import br.com.caelum.ingresso.helper.TokenHelper;
import br.com.caelum.ingresso.mail.EmailNovoUsuario;
import br.com.caelum.ingresso.mail.Mailer;
import br.com.caelum.ingresso.mail.Token;
import br.com.caelum.ingresso.model.Usuario;
import br.com.caelum.ingresso.model.form.ConfirmacaoLoginForm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class UsuarioController {

    private final Mailer mailer;

    private final TokenHelper tokenHelper;

    private final UsuarioDao usuarioDao;

    public UsuarioController(Mailer mailer, TokenHelper tokenHelper, UsuarioDao usuarioDao) {
        this.mailer = mailer;
        this.tokenHelper = tokenHelper;
        this.usuarioDao = usuarioDao;
    }


    @GetMapping("/usuario/request")
    public ModelAndView form(){
        return new ModelAndView("usuario/form-email");
    }

    @PostMapping("/usuario/request")
    @Transactional
    public ModelAndView acesso(String email){
        ModelAndView mnv = new ModelAndView("usuario/adicionado");

        Token token = tokenHelper.generateFrom(email);

        mailer.send(new EmailNovoUsuario(token));

        return mnv;
    }

    @GetMapping("usuario/validate")
    public ModelAndView validaLink(@RequestParam("uuid") String uuid){

        Optional<Token> optionalToken = tokenHelper.getTokenFrom(uuid);

        if(!optionalToken.isPresent()){
            ModelAndView mvn = new ModelAndView("redirect:/login");

            mvn.addObject("msg", "O token do link utilizado nao foi encontrado!");

            return mvn;
        }

        Token token = optionalToken.get();
        ConfirmacaoLoginForm confirmacaoLoginForm = new ConfirmacaoLoginForm(token);
        ModelAndView confirmacaoView = new ModelAndView("usuario/confirmacao");
        confirmacaoView.addObject("confirmacaoLoginForm", confirmacaoLoginForm);
        return confirmacaoView;
    }

    @PostMapping("usuario/cadastrar")
    public ModelAndView cadastrar(ConfirmacaoLoginForm confirmacaoLoginForm){
        ModelAndView mnv = new ModelAndView("redirect:/login");

        if(confirmacaoLoginForm.isValid()){
            Usuario usuario = confirmacaoLoginForm.toUsuario(usuarioDao, new BCryptPasswordEncoder());

            usuarioDao.save(usuario);

            mnv.addObject("msg", "Usuario cadastrado com sucesso!");

            return mnv;
        }

        mnv.addObject("msg", "O token do link utilizado nao foi encontrado");
        return mnv;
    }

}

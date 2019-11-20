package br.com.caelum.ingresso.model;


import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class Carrinho{

    private List<Ingresso> ingressos = new ArrayList<>();

    public void add(Ingresso ingresso){
        this.ingressos.add(ingresso);
    }
}
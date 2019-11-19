package br.com.caelum.ingresso.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class Carrinho {

    private Integer id;

    private List<Ingresso> ingressos = new ArrayList<>();

    public void add(Ingresso ingresso){
        this.ingressos.add(ingresso);
    }

    public boolean isSelecionado(Lugar lugar){
        return ingressos.stream().map(Ingresso::getLugar).anyMatch(l -> l.equals(lugar));
    }
}

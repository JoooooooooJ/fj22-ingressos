package br.com.caelum.ingresso.model.form;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarrinhoForm{

    private List<Ingresso> ingressos = new ArrayList<>();

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }

    public List<Ingresso> toIngressos(SessaoDao sessaoDao, LugarDao lugarDao){
        return ingressos.stream().map(ingresso -> {
            Sessao s = sessaoDao.findOne(ingresso.getSessao().getId());
            Lugar l = lugarDao.findOne(ingresso.getLugar().getId());
            TipoDeIngresso t = ingresso.getTipoDeIngresso();
            return new Ingresso(s,t,l);
        }).collect(Collectors.toList());
    }
}
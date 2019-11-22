package br.com.caelum.ingresso.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Permissao {

    @Id
    private String nome;

    public Permissao(String nome) {
        this.nome = nome;
    }

    /**
     * @deprecated hibernate only*/

    public Permissao(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

package br.com.caelum.ingresso.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Permissao implements GrantedAuthority {

    @Id
    private String nome;

    public static Permissao COMPRADOR = new Permissao("ROLE_COMPRADOR");
    public static Permissao ADMIN = new Permissao("ROLE_ADMIN");

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

    @Override
    public String getAuthority() {
        return nome;
    }
}

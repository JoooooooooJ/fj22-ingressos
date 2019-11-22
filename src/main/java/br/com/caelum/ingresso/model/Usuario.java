package br.com.caelum.ingresso.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario {

    @Id
    @GeneratedValue
    private Integer id;

    private String email;
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Permissao> permisoses = new HashSet<>();

    public Usuario(String email, String senha, Set<Permissao> permisoses) {
        this.email = email;
        this.senha = senha;
        this.permisoses = permisoses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Permissao> getPermisoses() {
        return permisoses;
    }

    public void setPermisoses(Set<Permissao> permisoses) {
        this.permisoses = permisoses;
    }
}

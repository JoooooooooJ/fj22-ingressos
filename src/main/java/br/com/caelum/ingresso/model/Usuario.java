package br.com.caelum.ingresso.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permisoses;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

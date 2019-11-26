package br.com.caelum.ingresso.dao;

import br.com.caelum.ingresso.model.Usuario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UsuarioDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Usuario usuario){
       if(usuario.getId()==null)
           entityManager.persist(usuario);
       else
           entityManager.merge(usuario);
    }

    public Optional<Usuario> findByEmail(String email){
        return entityManager
                .createQuery("select u from Usuario u where u.email = :email", Usuario.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst()
                ;
    }
}

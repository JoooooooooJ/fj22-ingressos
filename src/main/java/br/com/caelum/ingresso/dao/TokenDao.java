package br.com.caelum.ingresso.dao;

import br.com.caelum.ingresso.mail.Token;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class TokenDao
{
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Token token){
        entityManager.persist(token);
    }
    public Optional<Token> findByUuid(String uuid){
        return entityManager
                .createQuery("select t from Token t where t.uuid =: uuid", Token.class)
                .setParameter("uuid", uuid)
                .getResultList()
                .stream()
                .findFirst()
                ;
    }
}

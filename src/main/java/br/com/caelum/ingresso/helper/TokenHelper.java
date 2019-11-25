package br.com.caelum.ingresso.helper;

import br.com.caelum.ingresso.dao.TokenDao;
import br.com.caelum.ingresso.mail.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenHelper {

    @Autowired
    private TokenDao tokenDao;

    public Token generateFrom(String email){
        Token token = new Token(email);

        tokenDao.save(token);

        return token;
    }

    public Optional<Token> getTokenFrom(String uuid){
        return tokenDao.findByUuid(uuid);
    }
}

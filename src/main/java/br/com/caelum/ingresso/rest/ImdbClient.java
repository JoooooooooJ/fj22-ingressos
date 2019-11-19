package br.com.caelum.ingresso.rest;

import br.com.caelum.ingresso.model.DetalhesDoFilme;
import br.com.caelum.ingresso.model.Filme;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class ImdbClient {
    private Logger logger = Logger.getLogger(ImdbClient.class);

    public  <T> Optional<T>  request(Filme filme, Class<T> tClass){
        RestTemplate client = new RestTemplate();

        String titulo = filme.getNome().toLowerCase().replace(" ","+");

        String url = String.format("http://www.omdbapi.com/?apikey=f17c0176&t=%s", titulo);

        try {
            return Optional.of(client.getForObject(url, tClass));
        }catch (RestClientException e){
            logger.error(e.getMessage(), e);
            return Optional.empty();
        }
    }
}

package br.com.caelum.ingresso.mail;

public interface Email {
    String getTo();
    String getBody();
    String getSubject();
}

package br.com.caelum.ingresso.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class Mailer {

    @Autowired
    private JavaMailSender javaMailSender;

    private final String from = "Ingresso<cursofj22@gmail.com>";

    public void send(Email email){
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);

        try {
            messageHelper.setFrom(from);
            messageHelper.setTo(email.getTo());
            messageHelper.setSubject(email.getSubject());
            messageHelper.setText(email.getBody(), true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

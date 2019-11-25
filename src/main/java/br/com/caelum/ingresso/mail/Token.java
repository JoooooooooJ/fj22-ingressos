package br.com.caelum.ingresso.mail;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.util.UUID;

@Entity
public class Token {

    @Id
    private String uuid;

    @Email
    private String email;
    /**
     * @deprecated hibernate only
     * */
    public Token(){}

    public Token(String email){
        this.email = email;
    }

    public String getUserId() {
        return uuid;
    }

    public void setUserId(String userId) {
        this.uuid = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @PrePersist
    public void prePersist(){
        uuid = UUID.randomUUID().toString();
    }
}

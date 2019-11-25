package br.com.caelum.ingresso.mail;

public class EmailNovoUsuario implements Email {

    private final Token token;

    public EmailNovoUsuario(Token token) {
        this.token = token;
    }

    @Override
    public String getTo() {
        return token.getEmail();
    }

    @Override
    public String getBody() {
        StringBuilder body = new StringBuilder("<html>");

        body.append("<body>");
        body.append("<h2>Bem-Vindo!</h2>");
        body.append(String.format("Acesse o <a href=%s>link</a> para confirmar seu cadastro no sistema de ingresso!", makeURL()));
        body.append("</body>");
        body.append("</html>");
        return body.toString();
    }

    @Override
    public String getSubject() {
        return "Cadastro Sistema de ingressos";
    }

    public String makeURL(){
        return String.format("http://localhost:8080/usuario/validade?uuid=%s", token.getUserId());
    }
}

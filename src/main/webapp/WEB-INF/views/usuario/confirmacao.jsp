<%--
  Created by IntelliJ IDEA.
  User: RYZEN 2600
  Date: 25/11/2019
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="ingresso" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ingresso:template>
    <jsp:body>
        <form action="/usuario/cadastrar" method="post">
            <span class="text-danger">${param.error}</span>

            <input type="hidden" name="token.uuid" value="${confirmacaoLoginForm.token.userId}">
            <input type="hidden" name="token.email" value="${confirmacaoLoginForm.token.email}">

            <div class="form-group">
                <label for="password">Senha:</label>
                <input id="password" type="password" name="password" class="form-control">
            </div>

            <div class="form-group">
                <label for="confirmPassword">Senha:</label>
                <input id="confirmPassword" type="password" name="confirmPassword" class="form-control">
            </div>

            <button class="btn btn-primary" type="submit">Cadastrar</button>

        </form>
    </jsp:body>
</ingresso:template>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrar receita</title>
<%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="container">
    <h1>Cadastro de receita</h1>
    <c:if test="${not empty msg }">
        <div class="alert alert-success">${msg}</div>
    </c:if>
    <c:if test="${not empty erro }">
        <div class="alert alert-danger">${erro}</div>
    </c:if>
    <form action="receita" method="post">
        <input type="hidden" value="cadastrar" name="acao">
        <div class="form-group">
            <label for="id-descricao">Descrição</label>
            <input type="text" name="nome" id="id-nome" class="form-control" placeholder="Informe a descrição">
        </div>
        <div class="form-group">
            <label for="id-valor">Valor</label>
            <input type="text" name="valor" id="id-valor" class="form-control" placeholder="Informe o valor">
        </div>
        <div class="form-group">
            <label for="id-data">Data da Receita (dd/mm/aaaa)</label>
            <input type="text" name="data" id="id-data" class="form-control" placeholder="Ex: 23/08/1991">
        </div>
        <div class="form-group">
            <label for="id-categoria">Categoria</label>
            <input type="text" name="categoria" id="id-categoria" class="form-control" placeholder="Informe a categoria">
        </div>
        <input type="submit" value="Salvar" class="btn btn-primary">
    </form>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>

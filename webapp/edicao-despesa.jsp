<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Atualização de Despesa</title>
    <%@ include file="header.jsp" %>
</head>
<body>
    <%@ include file="menu.jsp" %>
    <div class="container">
        <h1>Edição de Despesa</h1>
        <form action="despesa" method="post">
            <input type="hidden" value="editar" name="acao">
            <input type="hidden" value="${despesa.codigo}" name="codigo">
            <div class="form-group">
                <label for="id-descricao">Descrição</label>
                <input type="text" name="descricao" id="id-descricao" class="form-control" value="${despesa.descricao}" placeholder="Informe a descrição">
            </div>
            <div class="form-group">
                <label for="id-valor">Valor</label>
                <input type="text" name="valor" id="id-valor" class="form-control" value="${despesa.valor}" placeholder="Informe o valor">
            </div>
            <div class="form-group">
                <label for="id-data">Data da Despesa</label>
                <input type="text" name="data" id="id-data" class="form-control" 
                    value='<fmt:formatDate value="${despesa.dataDespesa.time}" pattern="dd/MM/yyyy"/>' placeholder="dd/mm/yyyy" >
            </div>
            <div class="form-group">
                <label for="id-categoria">Categoria</label>
                <input type="text" name="categoria" id="id-categoria" class="form-control" value="${despesa.categoria}" placeholder="Informe a categoria">
            </div>          
                        
            <input type="submit" value="Salvar" class="btn btn-primary">
            <a href="despesa?acao=listar" class="btn btn-danger">Cancelar</a>
        </form>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>

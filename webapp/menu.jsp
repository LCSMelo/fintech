<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

<!-- Bootstrap CSS CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

<style>
    .navbar {
        background-color: #2461b1; /* Substitua pela cor desejada */
        color: #000; /* Substitua pela cor desejada */
    }

    .social-icons a {
        color: #fff; /* Substitua pela cor desejada */
        margin-right: 10px;
    }
    
        .nav-link-bold {
        font-weight: bold;
        color: #000;
    }
</style>


<nav class="navbar navbar-expand-lg navbar-dark">
  	
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
		<a class="nav-link nav-link-bold" href="home.jsp">FINTECH</a>      
	  </li>
      <li class="nav-item">
        <a class="nav-link" href="despesa?acao=abrir-form-cadastro">Cadastrar despesa</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="receita?acao=abrir-form-cadastro">Cadastrar receita</a>
      </li>      
      <li class="nav-item">
        <a class="nav-link" href="despesa?acao=listar">Despesas</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="receita?acao=listar">Receitas</a>
      </li>
      
    </ul>
    <c:if test="${empty user }">
	    <span class="navbar-text text-danger" style="margin-right:10px" >
	        ${erro }
	  	</span>	
	    <form class="form-inline my-2 my-lg-0" action="login" method="post">
	    	  <input class="form-control mr-sm-2" type="text" name="email" placeholder="E-mail">
	      <input class="form-control mr-sm-2" type="password" name="senha" placeholder="Senha">
	      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Entrar</button>
	    </form>
    </c:if>
    <c:if test="${not empty user }">
    		<span class="navbar-text">
	    		${user }
	    		<a href="login" class="btn btn-outline-primary my-2 my-sm-0">Sair</a>
	  	</span>	
    </c:if>
  </div>
</nav>
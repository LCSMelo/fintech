<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="home.jsp">Fintech</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="despesa?acao=abrir-form-cadastro">Cadastro</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="despesa?acao=listar">Despesas</a>
      </li>
    </ul>
    <c:if test="${empty user }">
	    <span class="navbar-text text-write" style="margin-right:10px" >
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

	    <section class="container mt-5">
        <div class="row d-flex justify-content-center align-items-center">
            <div class="col-lg-6">
                <h2 class="my-title display-5 my-4"> Soluções modernas para o crescimento do seu patrimônio</h2>
                <h3 class="mb-4">Somos um time de realizadores de sonhos em busca de inovações financeiras para
                    facilitar a sua vida</h3>
                <a href="" class="btn btn-primary btn-lg mb-5">Mais informações <i class="fa-solid fa-arrow-right-long ms-1"></i></a>
            </div>
            <div class="col-lg-6 text-center">
                <img src="./images/team.jpg" alt="" class="img-fluid rounded">
            </div>
        </div>
    </section>


    <!-- Corpo 2o componente -->
    <section class="container my-5">
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <div class="col">
                <div class="card">
                    <img src="./images/pexels-tobias-bjørkli-2239487.jpg" class="card-img-top" alt="Cofrinho de porco">
                    <div class="card-body">
                        <h5 class="card-title">Recebimentos</h5>
                        <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi enim
                            distinctio nostrum aperiam, maxime dolorum.</p>
                        <a href="receita?acao=abrir-form-cadastro" class="btn btn-primary">Registrar</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <img src="./images/pexels-pixabay-433333.jpg" class="card-img-top" alt="Livros">
                    <div class="card-body">
                        <h5 class="card-title">Educação</h5>
                        <p class="card-text">Lorem ipsum dolor sit amet consectetur adipisicing elit. Vero non tenetur
                            libero modi doloribus impedit.</p>
                        <a href="#" class="btn btn-primary">Aprender</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <img src="./images/pexels-pixabay-53621.jpg" class="card-img-top" alt="Calculadora e caneta">
                    <div class="card-body">
                        <h5 class="card-title">Despesas</h5>
                        <p class="card-text">Lorem ipsum dolor sit amet consectetur adipisicing elit. Vero non tenetur
                            libero modi doloribus impedit.</p>
                        <a href="despesa?acao=abrir-form-cadastro" class="btn btn-primary">Registrar</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="fr">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">

<title>Connection</title>
<!-- Bootstrap core CSS -->
<link href="../../../vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../../../css/4-col-portfolio.css" rel="stylesheet">

<!-- Google code prettify -->
<link rel="stylesheet" type="text/css"
	href="../../../vendor/google-code-prettify/prettify.css">
</head>

<body>
	<br>
	<header>
		<div>
			<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
				<div class="container">
					<a class="navbar-brand"
						href="${pageContext.request.contextPath}/ServletPageEncheres1">ENI-Encheres_Projet_JLS</a>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarResponsive" aria-controls="navbarResponsive"
						aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarResponsive">
						<ul class="navbar-nav ml-auto">
							<li class="nav-item active">
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/ServletInscription"><button
										type="button" class="btn btn-primary btn-lg btn-block">S'inscrire
									</button></a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/ServletConnection"><button
										type="button" class="btn btn-primary btn-lg btn-block">Se
										connecter</button></a></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
	</header>
	<!-- Page Content -->
	<div class="container">

		<!-- Page Heading -->
		<br> <br>
		<h1 class="my-4 text-center">
			<span>Vous êtes sur la page de connection</span>
		</h1>


		<form method="post" action="./ServletConnection">
			<div class="form-row">
				<div class="col-md-6 mb-3">
					<label for="pseudo">Identifiant :</label> <input type="text"
						class="form-control " id="pseudo" name="pseudo"
						value="${param.pseudo }"> <span class="erreur">${erreurs['pseudo']}</span>

				</div>

			</div>
			<div class="form-row">
				<div class="col-md-6 mb-3">
					<label for="password">Mot de passe</label> <input type="password"
						class="form-control " id="password" name="password"
						value="${param.password }">

				</div>

			</div>
			<button class="btn btn-primary" type="submit">Connection</button>

		</form>
		<br>
		<p class=" text-danger ${empty erreurs ? 'succes' : 'erreur'}">
			<strong>${resultat}</strong>
		</p>
		<div class="form-group">
			<div class="form-check">
				<a href="${pageContext.request.contextPath}/ServletMotdePassePerdu">Mot
					de Passe oublié</a>
			</div>
		</div>



	</div>


	<br>
	<br>
	<!-- Footer -->
	<footer class="py-5 bg-dark footer-demodule">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Team_JLS
				2020</p>
		</div>
		<!-- /.container -->
	</footer>
	<!-- Optional JavaScript; choose one of the two! -->

	<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
		crossorigin="anonymous"></script>

	<!-- Option 2: jQuery, Popper.js, and Bootstrap JS
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    -->
</body>
</html>

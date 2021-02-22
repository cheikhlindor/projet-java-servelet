<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<title>Mon Profil</title>
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
						href="${pageContext.request.contextPath}/ServletPageEncheres1">ENI-Encheres</a>
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
	<br>
	<br>
	<div class="container">
		<br>
		<br>
		<fieldset>
			<p>Vous pouvez vous inscrire via ce formulaire.</p>
			<legend>Inscription</legend>
			<form method="post" action="./ServletInscription">
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="identifiant">Pseudo :<span class="requis">*</span></label>
						<input type="text" class="form-control " id="identifiant"
							name="identifiant" value="<c:out value="${param.identifiant}"/>"
							required> <span class="erreur">${erreurs['identifiant']}</span>
					</div>
					<div class="col-md-6 mb-3">
						<label for="nom">Nom :<span class="requis">*</span></label> <input
							type="text" class="form-control " id="nom" name="nom"
							value="<c:out value="${param.nom}"/>" required> <span
							class="erreur">${erreurs['nom']}</span>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="prenom">Prenom :<span class="requis">*</span></label>
						<input type="text" class="form-control " id="prenom" name="prenom"
							value="<c:out value="${param.prenom}"/>" required> <span
							class="erreur">${erreurs['prenom']}</span>
					</div>
					<div class="col-md-6 mb-3">
						<label for="email">Email :<span class="requis">*</span></label> <input
							type="text" class="form-control " id="email" name="email"
							value="<c:out value="${param.email}"/>" required> <span
							class="erreur">${erreurs['email']}</span>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="telephone">Télephone :<span class="requis">*</span></label>
						<input type="text" class="form-control " id="telephone"
							name="telephone" value="" required>

					</div>
					<div class="col-md-6 mb-3">
						<label for="rue">Rue :<span class="requis">*</span></label> <input
							type="text" class="form-control " id="rue" name="rue" value=""
							required>

					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="codepostal">code Postal :<span class="requis">*</span></label>
						<input type="text" class="form-control " id="codepostal"
							name="codepostal" value="" required>

					</div>
					<div class="col-md-6 mb-3">
						<label for="ville">Ville :<span class="requis">*</span></label> <input
							type="text" class="form-control " id="ville" name="ville"
							value="" required>

					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label for="motdepasse">Mot de Passe :<span class="requis">*</span></label>
						<input type="password" class="form-control " id="motdepasse"
							name="motdepasse" value="" required> <span class="erreur">${erreurs['motdepasse']}</span>
					</div>
					<div class="col-md-6 mb-3">
						<label for="Confirmation">Confirmation :<span
							class="requis">*</span></label> <input type="password"
							class="form-control " id="Confirmation" name="Confirmation"
							value="" required> <strong><span class="erreur">${erreurs['Confirmation']}</span></strong>
					</div>
				</div>
				<button class="btn btn-primary" type="submit">Créer</button>
				<a href="${pageContext.request.contextPath}/Acceuil">Annuler</a>
			</form>
			<br>
			 <p class="text-danger ${empty erreurs ? 'succes' : 'erreur'}"><strong >${resultat}</strong></p>
		</fieldset>
	</div>
	<br>
	<br>
	<br>
	<footer class="py-5 bg-dark footer-demodule">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; ENI Ecole
				2018</p>
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
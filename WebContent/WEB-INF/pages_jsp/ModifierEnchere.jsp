<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
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

<title>Modifier enchère</title>
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

	<header>
		<c:if test="${!empty sessionScope.pseudo && !empty sessionScope.nom}">
			<div>
				<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
					<div class="container">
						<a class="navbar-brand"
							href="${pageContext.request.contextPath}/ServletPageEncheres1">ENI-Encheres_Projet_JLS</a>
						<button class="navbar-toggler" type="button"
							data-toggle="collapse" data-target="#navbarResponsive"
							aria-controls="navbarResponsive" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>
						<div class="collapse navbar-collapse" id="navbarResponsive">
							<ul class="navbar-nav ml-auto">
								<li class="nav-item active">
								<li class="nav-item"><a class="nav-link"
									href="${pageContext.request.contextPath}/ServletPageEncheres1">Enchères
								</a></li>
								<li class="nav-item"><a class="nav-link"
									href="${pageContext.request.contextPath}/article/creation">Vendre
										un article </a></li>
								<li class="nav-item"><a class="nav-link"
									href="${pageContext.request.contextPath}/ServletProfil">Mon
										Profil </a></li>
								<li class="nav-item"><a class="nav-link"
									href="${pageContext.request.contextPath}/ServletDeconnection">Déconnexion
								</a></li>
								<li class="nav-item"><a class="nav-link"
									href="${pageContext.request.contextPath}/ServletProfil">${ sessionScope.nom }
										connecté(e)</a></li>
							</ul>
						</div>
					</div>
				</nav>
			</div>

		</c:if>
		<c:if test="${empty sessionScope.pseudo && empty sessionScope.nom}">
			<div>
				<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
					<div class="container">
						<a class="navbar-brand"
							href="${pageContext.request.contextPath}/encheres">ENI-Encheres_Projet_JLS</a>
						<button class="navbar-toggler" type="button"
							data-toggle="collapse" data-target="#navbarResponsive"
							aria-controls="navbarResponsive" aria-expanded="false"
							aria-label="Toggle navigation">
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
		</c:if>
	</header>
	<br>
	<br>

	<div class="container">
		<h1 class="my-4 text-center">MODIFIER ENCHERE</h1>
	</div>

	<div class="container">
		<br> <br>
		<c:if test="${!empty listeCodesErreur}">
			<div class="alert alert-danger" role="alert">
				<strong>Erreur!</strong>
				<ul>
					<c:forEach var="code" items="${listeCodesErreur}">
						<li>${LecteurMessage.getMessageErreur(code)}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		<form method="POST" action="./enregistrerModificationEnchere">
			<div class="form-row">
				<div class="col-lg-5 md-6 mb-3 ">
					<label for="image_Article">photo :</label> <input type="image"
						id="photoArticle" src="">

				</div>
				<div class="col-lg-5  offset-lg-1 md-6 mb-6">
					<label for="Article">Article :</label> <input type="text"
						class="form-control " id="Article" name="Article"
						value="<c:out value="${sessionScope.nomArticle}"/>">

				</div>
			</div>
			<div class="form-row">
				<div class="col-lg-5 md-6 offset-lg-6">
					<label for="Description">Description :</label>
					<textarea rows="5" cols="33" class="form-control " id="Descritpion"
						name="Description"
						value="<c:out value="${sessionScope.description}"/>">
    				  </textarea>
				</div>

			</div>
			<div class="form-row">
				<div class="col-lg-5 md-6 offset-lg-6">
					<label for="Categorie">Categorie :</label> <select
						class="custom-select" name="categories" id="inputGroupSelect01"
						value="<c:out value="${sessionScope.categorie}"/>">
						<option selected>Toutes...</option>
						<option value="1">Objets</option>
						<option value="2">Electronique</option>
						<option value="3">Electromenager</option>
						<option value="4">Ameublement</option>
						<option value="5">Vetement</option>
						<option value="6">Sport & Loisir</option>
						<option value="7">Jeux & jouets</option>
					</select>
				</div>
			</div>

			<div class="form-row">
				<div class="col-lg-5 md-6 offset-lg-6">
					<label for="Image">Uploader une image :</label> <input
						class="form-control" type="file" id="Image" name="Image"
						accept="image/png, image/jpeg">
				</div>
			</div>

			<div class="form-row">
				<div class="col-lg-5 md-6 offset-lg-6">
					<label for="Mise_a_Prix">Mise a prix :</label> <input type="number"
						class="form-control " id="Mise_a_Prix" value="" name="Mise_a_Prix"
						value="<c:out value="${sessionScope.miseAPrix}"/>">
				</div>
			</div>
			<div class="form-row">
				<div class="col-lg-5 md-6 offset-lg-6">
					<label for="Date_Debut_Enchere">Début de l'enchère :</label> <input
						type="date" class="form-control " min="2020-11-07"
						max="2050-12-31" id="Date_Debut_Enchere" name="Date_Debut_Enchere"
						value="<c:out value="${sessionScope.dateDebutEnchere}"/>">
				</div>
			</div>
			<div class="form-row">
				<div class="col-lg-5 md-6 offset-lg-6">
					<label for="Date_Fin_Enchere">Fin de l'enchère :</label> <input
						type="date" class="form-control " min="2020-11-07"
						max="2050-12-31" id="Date_Fin_Enchere" name="Date_Fin_Enchere"
						value="<c:out value="${sessionScope.dateFinEnchere}"/>">
				</div>
			</div>

			<div class="col-md-6 mb-3">

				<input type="hidden" class="form-control " id="noUtilisateur"
					name="noUtilisateur" value="${sessionScope.noUtilisateur}">

			</div>
			<br> <br> <br>
			<div class="border border-info">
				<div class=" col-lg-5 md-6 offset-lg-6">
					<h4 class="my-4 text-center">
						<span>point de retrait</span>
					</h4>
					<div class="form-row">
						<div>
							<label for="Rue">Rue :</label> <input type="text"
								class="form-control " id="Rue" value="${sessionScope.rue}"
								name="Rue">

						</div>
					</div>
					<div class="form-row">
						<div>
							<label for="Code_Postal">Code postal :</label> <input type="text"
								class="form-control " value="${sessionScope.codePostal}"
								id="Code_Postal" name="Code_Postal">

						</div>
					</div>

					<div class="form-row">

						<div>
							<label for="Ville">Ville :</label> <input type="text"
								class="form-control " value="${sessionScope.ville}" id="Ville"
								name="Ville">
						</div>
					</div>
				</div>

			</div>
			<br> <br>
			<div class="form-row">
				<div class=" col-lg-5 md-12 offset-lg-6">
					<button class="btn btn-primary" type="submit">Enregistrer
						les modifications</button>


				</div>
			</div>
		</form>

	</div>




</body>


</html>







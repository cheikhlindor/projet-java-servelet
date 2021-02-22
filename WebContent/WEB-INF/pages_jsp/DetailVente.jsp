<%@page import="fr.eni.encheresprojetjls.bo.Articles"%>
<%-- <%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%> --%>
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

<!-- Bootstrap core CSS -->
<link href="../../../vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../../../css/4-col-portfolio.css" rel="stylesheet">

<!-- Google code prettify -->
<link rel="stylesheet" type="text/css"
	href="../../../vendor/google-code-prettify/prettify.css">


<title>Détail Vente</title>

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
		<h1 class="my-4 text-center">Détail vente</h1>

		<!-- Afficher photo de l'article ou pas d'article si pas de photo-->

		<%--     <c:if test="${empty photo}"> --%>
		<!--     	<h5>Pas de photo disponible pour cet article</h5> -->
		<%--     </c:if><br> --%>
		<div class="container">

			<div class="row">
				<div class="border border-info">
					<div class=" col-lg-6 md-6">

						<img
							src="https://studioart-photographe.fr/wp-content/uploads/2017/02/Sans-titre-3.jpg"
							class="card-img-top" alt="Accroche HTML">


					</div>
					<div class=" col-lg-6 md-6 ">
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
						<div class="card text-center">
							<p>${article.nomArticle }</p>
							<br />
							<p>Decription : ${article.description }</p>
							<br />
							<p>Catégorie : ${article.no_categorie }</p>
							<br />
							<p>Meilleure offre : ${sessionScope.Encheres.Montant_enchere}</p>
							<br />
							<p>Mise à prix : ${article.miseAPrix }</p>
							<br />
							<p>Fin de l'enchère : ${article.dateFinEncheres }</p>
							<br />
							<p>Retrait : ${vendeur.rue}</p>
							<p>Retrait : ${vendeur.codePostal}</p>
							<p>Retrait : ${vendeur.ville}</p>
							<br />
							<%-- 							<p>Vendeur : ${article.noUtilisateur }</p> --%>
							<input type="hidden" value="${article.noArticle}"
								name="noArticle"> <br />

							<form class="text-center" method="post" action="/encherir">
								<label>Ma proposition :</label>
								<div class="form-group">
									<input type="text" name="propositionEnchere"
										placeholder="Saisir votre proposition">
								</div>
								<button class="btn btn-primary" type="submit">Enchérir</button>
								<!-- afficher proposition enchère, btn submit enchère -->
							</form>
							<%-- 					<c:if test="${utilisateur.noUtilisateur} == ${article.noUtilisateur }"> --%>
							<!-- 							<form class="text-center" method="get" -->
							<!-- 								action="/modifierEnchere"> -->
							<!-- 								<button class="btn btn-primary" type="submit">Modifier</button> -->
							<!-- 							</form> -->
							<%-- 						</c:if> --%>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>





	<footer class="py-5 bg-dark footer-demodule">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Team_JLS
				2020</p>
		</div>
		<!-- /.container -->
	</footer>

</body>





</html>






package fr.eni.encheresprojetjls.servlets;

import java.io.IOException;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheresprojetjls.bll.ArticlesManager;
import fr.eni.encheresprojetjls.bll.CodesResultatBLL;
import fr.eni.encheresprojetjls.bo.Articles;
import fr.eni.encheresprojetjls.exception.BusinessException;

/**
 * Servlet implementation class Servlet_Creation_Article
 */
@WebServlet(urlPatterns = { "/article/creation" // redirige vers encheres1

})
public class ServletCreationArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Articles> articlesAAfficher = null;
		try {
			articlesAAfficher = new ArticlesManager().selectAll();
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			e.printStackTrace();
		}

		request.setAttribute("listecomplete", articlesAAfficher);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages_jsp/CreationArticle.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArticlesManager articlesManager = new ArticlesManager();
		
		String nomArticle = request.getParameter("Article").toLowerCase();
		String description = request.getParameter("Description").toLowerCase();
		Integer miseAPrix = Integer.parseInt(request.getParameter("Mise_a_Prix"));
		Integer prixVente = Integer.parseInt(request.getParameter("Mise_a_Prix"));
		LocalDate dateDebutEncheres = LocalDate.parse(request.getParameter("Date_Debut_Enchere"));
		LocalDate dateFinEncheres = LocalDate.parse(request.getParameter("Date_Fin_Enchere"));
		Integer categorie = Integer.parseInt(request.getParameter("categories"));
		Integer noUtilisateur = (Integer)request.getSession().getAttribute("noUtilisateur");
		Articles nouvelArticle = new Articles(categorie, noUtilisateur, nomArticle, description, dateDebutEncheres,
				dateFinEncheres, miseAPrix, prixVente);
		RequestDispatcher rd = null;
		try {
			articlesManager.creerUnArticleAVendre(nouvelArticle);
			rd = request.getRequestDispatcher("/ServletPageEncheres1");

		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			rd = request.getRequestDispatcher("/WEB-INF/pages_jsp/CreationArticle.jsp");
			e.printStackTrace();
		}
		rd.forward(request, response);

	}
}

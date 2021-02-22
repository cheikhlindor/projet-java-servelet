package fr.eni.encheresprojetjls.servlets;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheresprojetjls.bll.ArticlesManager;
import fr.eni.encheresprojetjls.bll.EncheresManager;
import fr.eni.encheresprojetjls.bll.UtilisateurManager;
import fr.eni.encheresprojetjls.bo.Articles;
import fr.eni.encheresprojetjls.bo.Encheres;
import fr.eni.encheresprojetjls.bo.Utilisateurs;
import fr.eni.encheresprojetjls.exception.BusinessException;

/**
 * Servlet implementation class ServletDetailVente
 */
@WebServlet(urlPatterns = { "/ServletDetailVente", "/encherir", "/modifierEnchere" })
public class ServletDetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String PROPOSITION_ENCHERE = "propositionEnchere";
	public static final String ATT_ERREURS = "erreurs";

	/**
	 * @param articleManager 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd =null;
		ArticlesManager articlesManager = new ArticlesManager();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Integer id = Integer.parseInt(request.getParameter("noArticle"));
		Integer idU = Integer.parseInt(request.getParameter("noUtilisateur"));
		Articles articleAAficher=null;
		try {
			articleAAficher = articlesManager.selectBYiD(id);
			Utilisateurs utliisateurvendeur = utilisateurManager.selectById(idU);
			request.setAttribute("vendeur", utliisateurvendeur);
			request.setAttribute("article", articleAAficher);
			rd = request.getRequestDispatcher("/WEB-INF/pages_jsp/DetailVente.jsp");
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			rd = request.getRequestDispatcher("/WEB-INF/pages_jsp/DetailVente.jsp");
			e.printStackTrace();
		}
		rd.forward(request, response);


			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
		
		
		if (request.getServletPath().equals("/encherir")) {
			// Convertir la proposition d'enchère en int
//			int montantEnchere = Integer.parseInt(request.getParameter(PROPOSITION_ENCHERE));
			EncheresManager encheresManager = new EncheresManager();
			Map<String, String> erreurs = new HashMap<String, String>();

			try {
//				validationEnchere(request);
//				insererEnchere(request)
			} catch (Exception e) {
				erreurs.put(PROPOSITION_ENCHERE, e.getMessage());
			}

			request.setAttribute(ATT_ERREURS, erreurs);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages_jsp/DetailVente.jsp");
			rd.forward(request, response);
		}

		else if (request.getServletPath().equals("/modifierEnchere")) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages_jsp/ModifierEnchere.jsp");
			rd.forward(request, response);
		}

		int propositionEnchere = Integer.parseInt(request.getParameter(PROPOSITION_ENCHERE));

		Map<String, String> erreurs = new HashMap<String, String>();
//		System.out.println(propositionEnchere);

		try {
			validationEnchere(propositionEnchere);
		} catch (Exception e) {
			erreurs.put(PROPOSITION_ENCHERE, e.getMessage());
		}

		request.setAttribute(ATT_ERREURS, erreurs);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages_jsp/DetailVente.jsp");
		rd.forward(request, response);

	}

	//
	public int insererEnchere(HttpServletRequest request) {

		int montantEnchere = Integer.parseInt(request.getParameter(PROPOSITION_ENCHERE));

		return montantEnchere;
	}

	/**
	 * Valide la proposition d'enchère saisie.
	 */
	private void validationEnchere(int propositionEnchere) throws BusinessException {

//		if (propositionEnchere <= 0 && propositionEnchere < derniereEnchere) {
//			throw new BusinessException();
//
//		}
		if (propositionEnchere <= 0 && propositionEnchere < Encheres.getMontantEnchere()) {
			throw new BusinessException();

		}
	}
}
package fr.eni.encheresprojetjls.servlets;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheresprojetjls.bll.ArticlesManager;
import fr.eni.encheresprojetjls.bo.Articles;
import fr.eni.encheresprojetjls.exception.BusinessException;

/**
 * Servlet implementation class ServletPageEncheres1
 */
@WebServlet(urlPatterns = { "/ServletPageEncheres1", }

)
public class ServletPageEncheres1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletPageEncheres1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		
			List<Articles> articlesAAfficher = null;
			try {
				articlesAAfficher = new ArticlesManager().selectAll();
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				e.printStackTrace();
			}
			request.setAttribute("listecomplete", articlesAAfficher);
			rd = request.getRequestDispatcher("/WEB-INF/pages_jsp/Page_Encheres1.jsp");
			rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		
		String motRecherche = request.getParameter("mot");
		Integer choixGroupe = Integer.parseInt(request.getParameter("choixgroupe"));
		System.out.println(request.getParameter("mot"));
		System.out.println(request.getParameter("choixgroupe"));
		List<Articles> articlesAAfficher = null;
		try {
			articlesAAfficher = new ArticlesManager().selectByRecherche(motRecherche,choixGroupe);
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			e.printStackTrace();
		}
		request.setAttribute("listecomplete", articlesAAfficher);
		rd = request.getRequestDispatcher("/WEB-INF/pages_jsp/Page_Encheres1.jsp");
		rd.forward(request, response);
	}
}


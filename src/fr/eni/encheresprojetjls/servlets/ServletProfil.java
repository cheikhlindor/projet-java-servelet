package fr.eni.encheresprojetjls.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheresprojetjls.bll.UtilisateurManager;
import fr.eni.encheresprojetjls.bo.Utilisateurs;

/**
 * Servlet implementation class ServletProfil
 */
@WebServlet(urlPatterns = { "/ServletProfil", "/modifier", "/enregistrer", "/supprimermoncompte" }

)
public class ServletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletProfil() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/ServletProfil")) {

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages_jsp/PageProfil.jsp");
			rd.forward(request, response);
		} else if (request.getServletPath().equals("/modifier")) {

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages_jsp/modifiersupprimerprofil.jsp");
			rd.forward(request, response);
		}

		if (request.getServletPath().equals("/supprimermoncompte")) {

			int no_utilisateur = (Integer) request.getSession().getAttribute("no_utilisateur");
			UtilisateurManager usermanager = new UtilisateurManager();
			
			try {

				usermanager.supprimerunutilisateur(no_utilisateur);
				RequestDispatcher rd = request.getRequestDispatcher("/ServletConnection");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

//			response.sendRedirect("ServletPageEncheres1");

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getServletPath().equals("/enregistrer")) {
			int no_utilisateur = Integer.parseInt(request.getParameter("no_utilisateur"));

			String pseudo = request.getParameter("identifiant");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			String rue = request.getParameter("rue");
			String codepostal = request.getParameter("codepostal");
			String ville = request.getParameter("ville");
			String motdepasse = request.getParameter("nouveaumotdepasse");
			String Confirmation = request.getParameter("Confirmation");
			String nouveaumotdepasse = request.getParameter("nouveaumotdepasse");

			UtilisateurManager usermanager = new UtilisateurManager();
			Utilisateurs user = new Utilisateurs(no_utilisateur, pseudo, nom, prenom, email, telephone, rue, codepostal,
					ville, nouveaumotdepasse);

			try {
				validationMotsDePasse(Confirmation, nouveaumotdepasse);
				usermanager.modifierprofil(user);
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				response.sendRedirect("ServletPageEncheres1");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private void validationMotsDePasse(String Confirmation, String nouveaumotdepasse) throws Exception {
		if (nouveaumotdepasse != null && nouveaumotdepasse.trim().length() != 0 && Confirmation != null
				&& Confirmation.trim().length() != 0) {
			if (!nouveaumotdepasse.equals(Confirmation)) {
				throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
			} else if (nouveaumotdepasse.trim().length() < 3) {
				throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");

			}
		} else {
			throw new Exception("Merci de saisir et confirmer votre mot de passe.");
		}
	}
}

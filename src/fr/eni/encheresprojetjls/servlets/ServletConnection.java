package fr.eni.encheresprojetjls.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Servlet implementation class ServletConnection
 */
@WebServlet(urlPatterns = {

		"/ServletConnection" }

)
public class ServletConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Integer> listeCodesErreur = new ArrayList<>();
	public static final String ATT_ERREURS = "erreurs";
	public static final String ATT_RESULTAT = "resultat";



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages_jsp/Connection.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		String identifiant = null;
		String password = null;
		String resultat = null;
		Map<String, String> erreurs = new HashMap<String, String>();

		try {

			try {
				identifiant = request.getParameter("pseudo");
				validationpseudo(identifiant);
				password = request.getParameter("password");
			} catch (Exception e) {
				e.printStackTrace();
				listeCodesErreur.add(CodesResultatServlets.FORMAT_USER_ERREUR);
			}

			Utilisateurs user = null;

			try {
				if (erreurs.isEmpty()) {

					user = utilisateurManager.connexion(password, identifiant);
					HttpSession session = request.getSession();
					session.setAttribute("noUtilisateur", user.getNoUtilisateur());
					
					session.setAttribute("pseudo", user.getPseudo());
					session.setAttribute("nom", user.getNom());
					session.setAttribute("prenom", user.getPrenom());
					session.setAttribute("email", user.getEmail());
					session.setAttribute("telephone", user.getTelephone());
					session.setAttribute("rue", user.getRue());
					session.setAttribute("codePostal", user.getCodePostal());
					session.setAttribute("ville", user.getVille());
					session.setAttribute("user", user);
					resultat = "Succès de connexion";
					request.setAttribute(ATT_ERREURS, erreurs);
					request.setAttribute(ATT_RESULTAT, resultat);
					response.sendRedirect("ServletPageEncheres1");
				} else {
					resultat = "vérifier vos champ de connexion et réessayer";

					request.setAttribute(ATT_ERREURS, erreurs);
					request.setAttribute(ATT_RESULTAT, resultat);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages_jsp/Connection.jsp");
					rd.forward(request, response);
					// response.sendRedirect("ServletConnection");
				}

			} catch (Exception e) {
				resultat = " Mot de passe ou identifiant incorrect vérifier vos champs de connexion et réessayer";
				request.setAttribute(ATT_ERREURS, erreurs);
				request.setAttribute(ATT_RESULTAT, resultat);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages_jsp/Connection.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
		}

	}

	private void validationpseudo(String pseudo) throws Exception {
		if (pseudo != null && pseudo.trim().length() < 3) {
			throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.");
		}
	}

}

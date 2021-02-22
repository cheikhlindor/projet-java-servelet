package fr.eni.encheresprojetjls.servlets;

import java.io.IOException;
import java.util.HashMap;
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
 * Servlet implementation class ServletInscription
 */
@WebServlet("/ServletInscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String PSEUDO = "identifiant";
	public static final String NOM = "nom";
	public static final String PRENOM = "prenom";
	public static final String EMAIL = "email";
	public static final String TELEPHONE = "telephone";
	public static final String RUE = "rue";
	public static final String CONDEPOSTAL = "codepostal";
	public static final String VILLE = "ville";
	public static final String MOTDEPASSE = "motdepasse";
	public static final String CONFIRMATION = "Confirmation";
	public static final String ATT_ERREURS = "erreurs";
	public static final String ATT_RESULTAT = "resultat";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher("/WEB-INF/pages_jsp/Inscription.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Récupération des champs du formulaire. */

		String pseudo = request.getParameter("identifiant");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter(TELEPHONE);
		String rue = request.getParameter(RUE);
		String codepostal = request.getParameter(CONDEPOSTAL);
		String ville = request.getParameter(VILLE);
		String motdepasse = request.getParameter(MOTDEPASSE);
		String Confirmation = request.getParameter(CONFIRMATION);
		UtilisateurManager usermanager = new UtilisateurManager();
		String resultat = null;
		Map<String, String> erreurs = new HashMap<String, String>();
		Utilisateurs u = new Utilisateurs(pseudo, nom, prenom, email, telephone, rue, codepostal, ville, motdepasse, 10,
				(byte) 1);

		try {

			try {

				validationMotsDePasse(motdepasse, Confirmation);
			} catch (Exception e) {
				erreurs.put(MOTDEPASSE, e.getMessage());
			}

			try {
				validationEmail(email);
			} catch (Exception e) {
				erreurs.put(EMAIL, e.getMessage());
			}

			/* Validation des champs mot de passe et confirmation. */

			/* Validation du champ nom. */
			try {
				validationNom(nom);
			} catch (Exception e) {
				erreurs.put(NOM, e.getMessage());
			}
			try {

				if (erreurs.isEmpty()) {

					usermanager.selectByemailverif(email);
					usermanager.selectByemailverif(email);
					usermanager.insererUnUser(u);
					HttpSession session = request.getSession();
					session.setAttribute("pseudo", pseudo);
					session.setAttribute("prenom", prenom);

					resultat = "Succès de l'inscription.";
					request.setAttribute(ATT_ERREURS, erreurs);
					request.setAttribute(ATT_RESULTAT, resultat);
					RequestDispatcher rd = request.getRequestDispatcher("/ServletPageEncheres1");
					rd.forward(request, response);

				} else {
					resultat = "vérifier vos champ de l'inscription et réessayer.";

					request.setAttribute(ATT_ERREURS, erreurs);
					request.setAttribute(ATT_RESULTAT, resultat);
					this.getServletContext().getRequestDispatcher("/WEB-INF/pages_jsp/Inscription.jsp").forward(request,
							response);
				}

			} catch (Exception e) {

				resultat = "Échec de l'inscription.";

				request.setAttribute(ATT_ERREURS, erreurs);
				request.setAttribute(ATT_RESULTAT, resultat);
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages_jsp/Inscription.jsp").forward(request,
						response);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Valide l'adresse mail saisie.
	 */
	private void validationEmail(String email) throws Exception {
		if (email != null && email.trim().length() != 0) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Merci de saisir une adresse mail valide.");
			}
		} else {
			throw new Exception("Merci de saisir une adresse mail.");
		}
	}

	/**
	 * Valide les mots de passe saisis.
	 */
	private void validationMotsDePasse(String motdepasse, String Confirmation) throws Exception {
		if (motdepasse != null && motdepasse.trim().length() != 0 && Confirmation != null
				&& Confirmation.trim().length() != 0) {
			if (!motdepasse.equals(Confirmation)) {
				throw new Exception("!!!Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
			} else if (motdepasse.trim().length() < 3) {
				throw new Exception("!!!Les mots de passe doivent contenir au moins 3 caractères.");

			}
		} else {
			throw new Exception("!!!Merci de saisir et confirmer votre mot de passe.");
		}
	}

	/**
	 * Valide le nom d'utilisateur saisi.
	 */
	private void validationNom(String nom) throws Exception {
		if (nom != null && nom.trim().length() < 3) {
			throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.");

		}
	}
}

package fr.eni.encheresprojetjls.bll;

import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.eclipse.jdt.internal.compiler.util.Messages;

import fr.eni.encheresprojetjls.bo.Utilisateurs;
import fr.eni.encheresprojetjls.dal.DAOFactory;
import fr.eni.encheresprojetjls.dal.UtilisateursDAO;
import fr.eni.encheresprojetjls.exception.BusinessException;





public class UtilisateurManager {
	private UtilisateursDAO daoutilisateur;
	private UtilisateurManager instance;

	public UtilisateurManager() {
		this.daoutilisateur = DAOFactory.getUtilisateursDAO();
	}

	public UtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}

	public List<Utilisateurs> afficherlesusers () throws BusinessException{
		return this.daoutilisateur.selectAll();
		
	}
	public void insererUnUser(Utilisateurs utilisateur) throws BusinessException {

		this.daoutilisateur.insererUnUser(utilisateur);
	}

	public boolean supprimerunutilisateur(int no_utilisateur) throws BusinessException {
		return this.daoutilisateur.deleteUnUser(no_utilisateur);

	}

	public Utilisateurs selectById(int no_utilisateur) throws BusinessException {
		return this.daoutilisateur.selectById(no_utilisateur);

	}

	public Utilisateurs connexion(String password, String identifiant) throws BusinessException {
		Utilisateurs utilisateur = daoutilisateur.connexion(password, identifiant);
		
		if (utilisateur==null || !(utilisateur.getActiver())) {
		
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.CONNECTION_ECHEC_AUTHENTIFICATION);
			throw businessException;
		
		}

		return utilisateur;


	}

	public void reinitialiser(Utilisateurs users) throws BusinessException {
		Utilisateurs user = new Utilisateurs();
		this.daoutilisateur.reinitialiser(user);
	}

	public Utilisateurs selectByemail(String email) throws BusinessException {
		return this.daoutilisateur.selectByemail(email);
	}

	public Boolean selectByemailverif(String email) throws Exception {

		int i = daoutilisateur.selectByemailverif(email);
		Boolean ok = true;
		if (i == 0) {
			ok = true;

		} else {
			ok = false;

			throw new Exception("un identifiant existe déjà avec le même email.");
		}
		System.out.println(i);

		return ok;
	}

	public boolean modifierprofil(Utilisateurs utilisateur) throws BusinessException {
		return this.daoutilisateur.modifierprofil(utilisateur);

	}

}

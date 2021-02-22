package fr.eni.encheresprojetjls.dal;

import java.util.List;

import fr.eni.encheresprojetjls.bo.Utilisateurs;
import fr.eni.encheresprojetjls.exception.BusinessException;



public interface UtilisateursDAO {
	public Utilisateurs selectById(int id) throws BusinessException;
	public void insererUnUser(Utilisateurs utilisateur) throws BusinessException;
	public boolean deleteUnUser(int id) throws BusinessException;
	public Utilisateurs connexion(String password, String identifiant) throws BusinessException;
	public boolean modifierprofil (Utilisateurs utilisateur) throws BusinessException;
	public void reinitialiser(Utilisateurs users) throws BusinessException;
	public Utilisateurs selectByemail(String email) throws BusinessException;
	public int selectByemailverif(String email) throws BusinessException;
	public List<Utilisateurs> selectAll()throws BusinessException;
	public void desactivercompte(int no_utilisateur)throws BusinessException;
}
 
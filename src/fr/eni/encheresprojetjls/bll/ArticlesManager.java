package fr.eni.encheresprojetjls.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.encheresprojetjls.bo.Articles;
import fr.eni.encheresprojetjls.dal.ArticlesVendusDAO;
import fr.eni.encheresprojetjls.dal.DAOFactory;
import fr.eni.encheresprojetjls.exception.BusinessException;


public class ArticlesManager {
private ArticlesVendusDAO articlesvendusDAO;
	
public ArticlesManager() {
	this.articlesvendusDAO= DAOFactory.getArticlesVendus();

}
public void creerUnArticleAVendre(Articles article)throws BusinessException{
	LocalDate jour = LocalDate.now();
	BusinessException businessException = new BusinessException();	
	if (article.getDateDebutEncheres().isAfter(article.getDateFinEncheres()) || article.getDateDebutEncheres()==null && article.getDateFinEncheres()==null) {
		businessException.ajouterErreur(CodesResultatBLL.DATE_FIN_DE_VENTE_NON_VALIDE);
	}
	if (article.getDateDebutEncheres().isAfter(jour)) {
		businessException.ajouterErreur(CodesResultatBLL.DATE_DEBUT_ENCHERE_NON_VALIDE);
	}
	if (article.getDescription().trim().length()>300) {
		businessException.ajouterErreur(CodesResultatBLL.DESCRIPTION_TROP_LONGUE);
	}
	if (article.getNomArticle() == null || article.getNomArticle().equals(" ") && (!article.getNomArticle().matches("^[a-zA-Z]*$"))) {
		businessException.ajouterErreur(CodesResultatBLL.ARTICLE_NOM_ERREUR);
	}
	if (!businessException.hasErreurs()) {
		articlesvendusDAO.insererUnArticle(article);
	}
	else {
		throw businessException;
	}
	
}
public  List<Articles> selectAll() throws BusinessException{
	if (articlesvendusDAO.selectAll().isEmpty()) {
		BusinessException businessException = new BusinessException();	
		businessException.ajouterErreur(CodesResultatBLL.SELECTION_DES_ARTICLES_IMPOSSIBLE);
		throw businessException;
	}
	return articlesvendusDAO.selectAll();
	
}
public List<Articles> selectByRecherche(String motRecherche, Integer choixGroupe)throws BusinessException {
	if (articlesvendusDAO.selectByRecherche(motRecherche, choixGroupe).isEmpty()) {
		BusinessException businessException = new BusinessException();	
		businessException.ajouterErreur(CodesResultatBLL.SELECTION_DES_ARTICLES_VIDE);
		throw businessException;
	}
	else {
		return articlesvendusDAO.selectByRecherche(motRecherche, choixGroupe);
	}
}
public Articles selectBYiD(Integer id) throws BusinessException{
	if (articlesvendusDAO.selectBYiD(id)==null) {
		BusinessException businessException = new BusinessException();	
		businessException.ajouterErreur(CodesResultatBLL.ERREUR_CHARGEMENT_INFORMATION_ARTICLE);
		throw businessException;
	}
	else {
		return articlesvendusDAO.selectBYiD(id);
	}
}



}

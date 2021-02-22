package fr.eni.encheresprojetjls.dal;

import fr.eni.encheresprojetjls.bo.Articles;
import fr.eni.encheresprojetjls.bo.Encheres;
import fr.eni.encheresprojetjls.exception.BusinessException;

public interface EncheresDAO {

	public void insererEncheres(Encheres encheres) throws BusinessException;
	public Encheres selectDernièreEnchere(int dernièreEnchere) throws BusinessException;
	Articles selectArticle(int no_article) throws BusinessException;
	void modifierEncheres(Encheres encheres);

}

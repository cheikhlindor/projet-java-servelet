package fr.eni.encheresprojetjls.dal;

import java.util.List;

import fr.eni.encheresprojetjls.bo.Articles;
import fr.eni.encheresprojetjls.exception.BusinessException;

public interface ArticlesVendusDAO {

	public void insererUnArticle(Articles article) throws BusinessException;
	public List<Articles> selectAll()throws BusinessException;
	public List<Articles> selectByRecherche(String motRecherche, Integer choixGroupe)throws BusinessException;
	public Articles selectBYiD(Integer id)throws BusinessException;

}

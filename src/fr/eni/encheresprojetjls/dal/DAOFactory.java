package fr.eni.encheresprojetjls.dal;

public abstract class DAOFactory {
	
	public static UtilisateursDAO getUtilisateursDAO()
	{
		return (UtilisateursDAO) new UtilisateurDAOJdbcImpl();
	}

	public static EncheresDAO getEncheresDAO() {
		return (EncheresDAO) new EncheresDAOJdbcImpl();
	}

public static ArticlesVendusDAO getArticlesVendus() {
	
	return (ArticlesVendusDAO) new ArticlesVendusDAOjdbcImlp();
	
}




}
	
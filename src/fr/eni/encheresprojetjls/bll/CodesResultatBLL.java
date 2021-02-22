package fr.eni.encheresprojetjls.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	
	/**
	 * Echec le nom de l'article ne respecte pas les règles définies
	 */
	public static final int REGLE_ARTICLE_NOM_ERREUR=20000;
	/**
	 * Echec le nom de l'article ne respecte pas les règles définies
	 */
	public static final int REGLE_LISTE_NOM_ERREUR = 20001;
	
	public static final int CONNECTION_ECHEC_AUTHENTIFICATION = 20002;

	public static final int DATE_FIN_DE_VENTE_NON_VALIDE = 20003;

	public static final int INSERT_OBJET_ECHEC = 20004;
	public static final int DATE_DEBUT_ENCHERE_NON_VALIDE = 20005;
	public static final int DESCRIPTION_TROP_LONGUE = 20006;
	
	public static final int ARTICLE_NOM_ERREUR=20007;
	public static final int SELECTION_DES_ARTICLES_IMPOSSIBLE = 20008;
	public static final int SELECTION_DES_ARTICLES_VIDE= 20009;
	public static final int ERREUR_CHARGEMENT_INFORMATION_ARTICLE = 20010;
}


package fr.eni.encheresprojetjls.bo;

import java.sql.Date;
import java.sql.Timestamp;

//Classe qui crée permet de ré-enchérir
public class Encheres {
	
	//Atributs

	private int noEnchere;

	// Atributs
	private static Timestamp dateEnchere;
	private static int montantEnchere;
	private int no_utilisateur;
	private int no_article;
	private Utilisateurs utilisateur;
	private Articles articles;

	// Constructeurs
	public Encheres() {
	}

	public Encheres(Timestamp dateEnchere, int montant_enchere) {
		super();
		Encheres.dateEnchere = dateEnchere;
		Encheres.montantEnchere = montant_enchere;
	}

	public Encheres(Timestamp dateEnchere, int montant_enchere, int no_utilisateur, int no_article) {
		super();
		Encheres.dateEnchere = dateEnchere;
		Encheres.montantEnchere = montant_enchere;
		this.no_utilisateur = no_utilisateur;
		this.no_article = no_article;
	}
	
//	public Articles Encheres(int no_article, String nom_article, String description, Date date_fin_encheres, int prix_initial, int prix_vente, int no_utilisateur) {
//		return this.articles;
//	}

	public Encheres(int no_enchere, Date date_enchere, int montant_enchere, int no_article, int no_utilisateur) {
	}
	
	//Getters et Setters	

	public int getNoEnchere() {
		return noEnchere;
	}

	public void setNoEnchere(int noEnchere) {
		this.noEnchere = noEnchere;
	}
	
	public int getNo_article() {
		return no_article;
	}
	
	public void setNo_article(int no_article) {
		this.no_article = no_article;
	}

	public static Timestamp getDateEnchere() {
		return dateEnchere;
	}
	
	public void setDateEnchere(String dateEnchere) {
		Encheres.dateEnchere = Timestamp.valueOf(dateEnchere);
	}

	public static int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montant_enchere) {
		Encheres.montantEnchere = montant_enchere;
	}

	@Override
	public String toString() {
		return "Encheres [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + "]";
	}

	public Utilisateurs getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateurs utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Articles getArticles() {
		return articles;
	}

	public void setArticles(Articles articles) {
		this.articles = articles;
	}

}

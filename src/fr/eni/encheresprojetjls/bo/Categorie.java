package fr.eni.encheresprojetjls.bo;

import java.util.List;

//Classe qui crée permet de renseigner la catégorie
public class Categorie {
	
	//Atributs
	private int noCategorie;
	private String libelle;
	List<Articles> listArticles;
	
	//Constructeurs
	public Categorie() {
		this.listArticles=listArticles;
	}

	public Categorie(int noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}
	
	//Getters et Setters
	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + "]";
	}
	
	

}

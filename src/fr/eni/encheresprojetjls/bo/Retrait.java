package fr.eni.encheresprojetjls.bo;

//Classe qui renseigne le lieu de retrait de l'article acheté
public class Retrait {
	
	//Atributs
	private String rue;
	private int codePostal;
	private String ville;
	private Articles  articles;
	
	//Constructeurs
	public Retrait() {
	}

	public Retrait(String rue, int codePostal, String ville) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	//Getters et Setters	
	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + "]";
	}
	
	

}

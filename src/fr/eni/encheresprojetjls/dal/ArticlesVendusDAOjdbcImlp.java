package fr.eni.encheresprojetjls.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheresprojetjls.bo.Articles;
import fr.eni.encheresprojetjls.exception.BusinessException;

public class ArticlesVendusDAOjdbcImlp implements ArticlesVendusDAO {
	private static final String INSERT_ARTICLES = "USE ENCHERES_BDD; INSERT INTO ARTICLES_VENDUS(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie) "
			+ "values(?,?,?,?,?,?,?,?);";
	private static final String SELECT_ALL_ARTICLES = "USE ENCHERES_BDD; SELECT * FROM ARTICLES_VENDUS;";
	private static final String SELECT_BYId_ARTICLES = "USE ENCHERES_BDD; SELECT * FROM ARTICLES_VENDUS where (no_categorie=?) OR( nom_article like ?);";
	private static final String SELECT_BYId_ARTICLES_EMPTY = "USE ENCHERES_BDD; SELECT * FROM ARTICLES_VENDUS where (no_categorie=?);";
	private static final String SELECT_BYId_ARTICLES_SEUL = "USE ENCHERES_BDD; SELECT * FROM ARTICLES_VENDUS where (no_article=?);";

	@Override
	public void insererUnArticle(Articles article) throws BusinessException {
		if (article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;

		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				pstmt = cnx.prepareStatement(INSERT_ARTICLES, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, article.getNomArticle());
				pstmt.setString(2, article.getDescription());
				pstmt.setDate(3, Date.valueOf(article.getDateDebutEncheres()));
				pstmt.setDate(4, Date.valueOf(article.getDateFinEncheres()));
				pstmt.setInt(5, article.getMiseAPrix());
				pstmt.setInt(6, article.getMiseAPrix());
				pstmt.setInt(7, article.getNoUtilisateur());
				pstmt.setInt(8, article.getNo_categorie());
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					article.setNoUtilisateur(rs.getInt(1));
				}
				rs.close();
				pstmt.close();

				cnx.commit();
			} catch (Exception e) {
				e.printStackTrace();

				cnx.rollback();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}

	public List<Articles> selectAll() throws BusinessException {

		List<Articles> tousLesArticles = new ArrayList<Articles>();

		try {
			Connection cnx = ConnectionProvider.getConnection();

			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLES);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Articles articlecourant = new Articles();
				articlecourant.setNo_categorie(rs.getInt("no_categorie"));
				articlecourant.setNomArticle(rs.getString("nom_article"));
				articlecourant.setDescription(rs.getString("description"));
				articlecourant.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				articlecourant.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				articlecourant.setMiseAPrix(rs.getInt("prix_initial"));
				articlecourant.setPrixVente(rs.getInt("prix_vente"));
				articlecourant.setNoUtilisateur(rs.getInt("no_utilisateur"));
				articlecourant.setNoArticle(rs.getInt("no_article"));
				tousLesArticles.add(articlecourant);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
			throw businessException;
		}
		return tousLesArticles;

	}

	@Override
	public List<Articles> selectByRecherche(String motRecherche, Integer choixGroupe) throws BusinessException {
		if (motRecherche.isEmpty()) {

		}
		List<Articles> tousLesArticles = new ArrayList<Articles>();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = null;
			if (motRecherche.isEmpty()) {
				pstmt = cnx.prepareStatement(SELECT_BYId_ARTICLES_EMPTY);
			} else {
				pstmt = cnx.prepareStatement(SELECT_BYId_ARTICLES);
				pstmt.setString(2, "%" + motRecherche.trim() + "%");
			}
			pstmt.setInt(1, choixGroupe);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Articles articlecourant = new Articles();
				articlecourant.setNo_categorie(rs.getInt("no_categorie"));
				articlecourant.setNomArticle(rs.getString("nom_article"));
				articlecourant.setDescription(rs.getString("description"));
				articlecourant.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				articlecourant.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				articlecourant.setMiseAPrix(rs.getInt("prix_initial"));
				articlecourant.setPrixVente(rs.getInt("prix_vente"));
				articlecourant.setNoUtilisateur(rs.getInt("no_utilisateur"));
				articlecourant.setNoArticle(rs.getInt("no_article"));
				tousLesArticles.add(articlecourant);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
			throw businessException;
		}
		return tousLesArticles;
	}

	@Override
	public Articles selectBYiD(Integer id) throws BusinessException {

		Articles articleAAfficher = new Articles();
		try {
			Connection cnx = ConnectionProvider.getConnection();

			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BYId_ARTICLES_SEUL);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				articleAAfficher.setNo_categorie(rs.getInt("no_categorie"));
				articleAAfficher.setNomArticle(rs.getString("nom_article"));
				articleAAfficher.setDescription(rs.getString("description"));
				articleAAfficher.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				articleAAfficher.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				articleAAfficher.setMiseAPrix(rs.getInt("prix_initial"));
				articleAAfficher.setPrixVente(rs.getInt("prix_vente"));
				articleAAfficher.setNoUtilisateur(rs.getInt("no_utilisateur"));
				articleAAfficher.setNoArticle(rs.getInt("no_article"));

			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
			throw businessException;

		}

		return articleAAfficher;
	}

}

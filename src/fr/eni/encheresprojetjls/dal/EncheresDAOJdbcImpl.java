package fr.eni.encheresprojetjls.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheresprojetjls.bo.Articles;
import fr.eni.encheresprojetjls.bo.Encheres;
import fr.eni.encheresprojetjls.exception.BusinessException;

public class EncheresDAOJdbcImpl implements EncheresDAO {
	
	private static final String INSERT_ENCHERE = "USE ENCHERES_BDD, INSERT INTO ENCHERES (date_enchere, montant_enchere, no_article, no_utilisateur) values(?,?,?,?)";
	private static final String SELECT_ARTICLE = "USE ENCHERES_BDD, SELECT no_article, nom_article,description, date_fin_encheres, prix_initial, prix_vente,"
			+ "no_utilisateur FROM ARTICLES_VENDUS";
	private static final String SELECT_DERNIERE_ENCHERE = "USE ENCHERES_BDD, SELECT * FROM ENCHERES WHERE no_enchere = ?";
//	private static final String UPDATE_ENCHERE = "USE ENCHERES_BDD, UPDATE "
	
	@Override
	public Encheres selectDernièreEnchere(int no_enchere) throws BusinessException {
		Encheres encheres = null;
		
		try {
			Connection cnx = ConnectionProvider.getConnection();
			
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_DERNIERE_ENCHERE);
			pstmt.setInt(1, no_enchere);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				encheres = new Encheres(rs.getInt("no_enchere"), rs.getDate("date_enchere"), rs.getInt("montant_enchere"), rs.getInt("no_article"), rs.getInt("no_utilisateur"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ECHEC);
			throw businessException;
		}
		return encheres;
		
	}
	
	
	
	@Override
	public void insererEncheres(Encheres encheres) throws BusinessException {
		if (encheres == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		try {
			Connection cnx = ConnectionProvider.getConnection();
			
			cnx.setAutoCommit(false);
			PreparedStatement pstmt;
			ResultSet rs;
						
//			if (Encheres.getMontantEnchere() > selectDernièreEnchere(no_enchere).getMontantEnchere) {
//				pstmt = cnx.prepareStatement(INSERT_ENCHERE, PreparedStatement.RETURN_GENERATED_KEYS);
//				pstmt.setTimestamp(1, Encheres.getDateEnchere());
//				pstmt.setInt(2, Encheres.getMontantEnchere());
//				pstmt.setInt(3, Articles.getNoArticle());
//				pstmt.setInt(4, Articles.getNo_utilisateur());
//				pstmt.executeUpdate();
//				rs = pstmt.getGeneratedKeys();
//				if (rs.next()) {
//					encheres.setNoEnchere(rs.getInt(1));
//				}
//				rs.close();
//				pstmt.close();
//			}
			
			cnx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
			
		}
		
	}

	@Override
	public void modifierEncheres(Encheres encheres) {
		
	}



	@Override
	public Articles selectArticle(int no_article) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}



}

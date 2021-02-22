package fr.eni.encheresprojetjls.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheresprojetjls.bo.Utilisateurs;
import fr.eni.encheresprojetjls.exception.BusinessException;





public class UtilisateurDAOJdbcImpl implements UtilisateursDAO {
	
	private static final String SELECT_ALL = "select pseudo, nom, prenom, email, telephone,rue, code_postal," + 
			" ville from UTILISATEURS ; ";

	private static final String SELECT_BY_ID = "select pseudo, nom, prenom, email, telephone,rue, code_postal,"
			+ " ville from UTILISATEURS where no_utilisateur=? ";

	private static final String INSERT_USER = "insert into UTILISATEURS(pseudo , nom, prenom, email, telephone,rue, code_postal,"
			+ "	 ville, mot_de_passe, credit,administrateur ) values(?,?,?,?,?,?,?,?,?,?,?);";

	private static final String CONNEXION = "select  no_utilisateur, pseudo, nom, prenom, email, mot_de_passe, telephone, rue,code_postal,ville,credit,administrateur , activer from UTILISATEURS "
			+ "where (pseudo=? OR email=?) AND (mot_de_passe=?) ;";

	private static final String DELETE_User = "update UTILISATEURS set activer=? where no_utilisateur=?";
	private static final String UPDATE_USER = "update UTILISATEURS set mot_de_passe=? where email=?;";
	private static final String SELECT_BY_EMAIL_VERIF = "select  count(email) as count from UTILISATEURS where email=? ;";
	private static final String SELECT_BY_EMAIL = "select  email,  mot_de_passe from UTILISATEURS  ;";
	private static final String MODIFIER_PROFIL = "update  UTILISATEURS set  pseudo=? , nom=?, prenom=?, email=?, telephone=?,rue=?, code_postal=?,ville=?, "
			+ "mot_de_passe=? where  no_utilisateur=?;";

	@Override
	public void insererUnUser(Utilisateurs utilisateur) throws BusinessException {
	
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;

				ResultSet rs;
				if (utilisateur.getNoUtilisateur() == 0) {
					pstmt = cnx.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);

					pstmt.setString(1, utilisateur.getPseudo());

					pstmt.setString(2, utilisateur.getNom());
					pstmt.setString(3, utilisateur.getPrenom());
					pstmt.setString(4, utilisateur.getEmail());
					pstmt.setString(5, utilisateur.getTelephone());
					pstmt.setString(6, utilisateur.getRue());
					pstmt.setString(7, utilisateur.getCodePostal());
					pstmt.setString(8, utilisateur.getVille());
					pstmt.setString(9, utilisateur.getMotDePasse());
					pstmt.setInt(10, utilisateur.getCredit());
					pstmt.setByte(11, utilisateur.getAdministrateur());
					pstmt.executeUpdate();
					rs = pstmt.getGeneratedKeys();
					if (rs.next()) {
						utilisateur.setNoUtilisateur(rs.getInt(1));
					}
					rs.close();
					pstmt.close();
				}

				cnx.commit();
				cnx.close();
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

	@Override
	public boolean deleteUnUser(int no_utilisateur) throws BusinessException {
		boolean rowdeleted;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_User);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, no_utilisateur);
			
			rowdeleted=pstmt.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_ARTICLE_ERREUR);
			throw businessException;
		}
		return rowdeleted;

	}

	@Override
	public Utilisateurs selectById(int no_utilisateur) throws BusinessException {
		Utilisateurs utilisateur = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, no_utilisateur);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				utilisateur = new Utilisateurs(rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"));

			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ECHEC);
			throw businessException;
		}

		return utilisateur;
	}

	@Override


	public Utilisateurs connexion(String password, String identifiant) throws BusinessException {
		Utilisateurs utilisateur = null;
		// instancie a null l'utilisateur
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(CONNEXION);
			pstmt.setString(1, identifiant);
			pstmt.setString(2, identifiant);
			pstmt.setString(3, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				utilisateur = new Utilisateurs(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getByte("administrateur"), rs.getBoolean("activer"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ECHEC);
			throw businessException;
		}
		return utilisateur;
	}

	@Override
	public void reinitialiser(Utilisateurs users) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_USER);

			pstmt.setString(1, users.getMotDePasse());
			pstmt.setString(1, users.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_ARTICLE_ERREUR);
			throw businessException;
		}
	}

	@Override
	public Utilisateurs selectByemail(String email) throws BusinessException {
		Utilisateurs utilisateur = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_EMAIL);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				utilisateur = new Utilisateurs(rs.getString("email"), rs.getString("mot_de_passe"));

			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ECHEC);
			throw businessException;
		}

		return utilisateur;
	}

	@Override
	public boolean modifierprofil(Utilisateurs utilisateur) throws BusinessException {
			boolean rowUpdated;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(MODIFIER_PROFIL);

			pstmt.setString(1, utilisateur.getPseudo());

			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getNoUtilisateur());
			
			
			rowUpdated = pstmt.executeUpdate()>0;
			

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ECHEC);
			throw businessException;
		}
		return rowUpdated;

		
	}

	@Override
	public int selectByemailverif(String email) throws BusinessException {
		int count = 0;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_EMAIL_VERIF);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				count = Integer.parseInt(rs.getString("count"));

			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ECHEC);
			throw businessException;
		}
		return count;

	}

	@Override
	public List<Utilisateurs> selectAll() throws BusinessException {
		Utilisateurs usercourant;
		List<Utilisateurs> tousLesusers = new ArrayList<Utilisateurs>();

		try {
			Connection cnx =ConnectionProvider.getConnection();
			
			PreparedStatement pstmt=cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				usercourant=new Utilisateurs(rs.getString("pseudo"), rs.getString("nom"), 
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"),
						rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
				
				tousLesusers.add(usercourant);
			}
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
			throw businessException;
		}
		return tousLesusers;
		
	}

	@Override
	public void desactivercompte(int no_utilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		
	}


	


}

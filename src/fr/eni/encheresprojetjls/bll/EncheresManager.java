package fr.eni.encheresprojetjls.bll;

import fr.eni.encheresprojetjls.bo.Encheres;
import fr.eni.encheresprojetjls.dal.DAOFactory;
import fr.eni.encheresprojetjls.dal.EncheresDAO;
import fr.eni.encheresprojetjls.exception.BusinessException;

public class EncheresManager {
	private EncheresDAO encheresDao;
	private EncheresManager instance;
	
	public EncheresManager() {
		this.encheresDao = DAOFactory.getEncheresDAO();
	}
	
	public EncheresManager getInstance() {
		if (instance == null) {
			instance = new EncheresManager();			
		}
		return instance;
	}
	
	public void insererEncheres(Encheres encheres) throws BusinessException {
		
		this.encheresDao = DAOFactory.getEncheresDAO();
	}
	
	public Encheres selectDernièreEnchere(int dernièreEnchere) throws BusinessException {
		
		return this.encheresDao.selectDernièreEnchere(dernièreEnchere);
	}

}

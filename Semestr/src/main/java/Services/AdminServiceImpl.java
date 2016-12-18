package Services;

import Classes.*;
import DAO.AchivementsDAO;
import DAO.DAOFactory;
import DAO.SpecialityDAO;
import DAO.UniversitiesDAO;
import Utils.MD5;
import Utils.Verifier;
import Utils.VerifierFactory;

/**
 * Created by Саоша on 17.12.2016.
 */
class AdminServiceImpl implements AdminService {

    private User admin;
    private Verifier verifier;
    private UniversitiesDAO univDAO;
    private SpecialityDAO specDAO;
    private AchivementsDAO achDAO;

    AdminServiceImpl() {
        admin = new User(0, "Admin", "Boss", " ", " ", "admin", MD5.getHash("R1987-2014"));
        verifier = VerifierFactory.getVerifier();
        univDAO = DAOFactory.getUniversitiesDAO();
        specDAO = DAOFactory.getSpecialityDAO();
        achDAO = DAOFactory.getAchivementsDAO();
    }

    @Override
    public User getAdmin() {
        return admin;
    }

    @Override
    public boolean checkAdmin(String login, String password) {
        if(login.equals(admin.getLogin()) && MD5.getHash(password).equals(admin.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addUniversity(University university) {
        univDAO.save(university);
    }

    @Override
    public void addSpeciality(Speciality speciality) {
        specDAO.save(speciality);
    }

    @Override
    public void addAchivement(Achivement achivement) {
        achDAO.save(achivement);
    }

    @Override
    public void updateUniversity(University university) {
        if(verifier.existUniversity(university.getId())) {
            univDAO.update(university);
        }
    }

    @Override
    public void updateSpeciality(Speciality specality) {
        if(verifier.existSpeciality(specality.getId())) {
            specDAO.update(specality);
        }
    }

    @Override
    public void updateAchivement(Achivement achivement) {
        if (verifier.existAchivement(achivement.getId())) {
            achDAO.update(achivement);
        }
    }

    @Override
    public void deleteUniversity(int univId) {
        if(verifier.existUniversity(univId)) {
            univDAO.delete(univId);
        }
    }

    @Override
    public void deleteSpeciality(int specId) {
        if(verifier.existSpeciality(specId)) {
            specDAO.delete(specId);
        }
    }

    @Override
    public void deleteAchivement(int achId) {
        if (verifier.existAchivement(achId)) {
            achDAO.delete(achId);
        }
    }
}

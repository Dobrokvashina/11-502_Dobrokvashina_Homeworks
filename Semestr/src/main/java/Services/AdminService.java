package Services;

import Classes.*;

/**
 * Created by Саоша on 07.12.2016.
 */
public interface AdminService {

    User getAdmin();

    boolean checkAdmin(String login, String password);

    void addUniversity(University university);

    void addSpeciality(Speciality speciality);

    void addAchivement(Achivement achivement);

    void updateUniversity(University university);

    void updateSpeciality(Speciality specality);

    void updateAchivement(Achivement achivement);

    void deleteUniversity(int univId);

    void deleteSpeciality(int specId);

    void deleteAchivement(int achId);
}


import DAO.AchivementsDAO;
import DAO.DAOFactory;
import DAO.ExtraPointsDAO;
import Services.AdminService;
import Services.ServiceFactory;

/**
 * Created by Саоша on 27.10.2016.
 */
public class Main {

    public static void main(String[] args) {

//        Properties properties = new Properties();
//
//        try {
//            properties.load(
//
//                    new FileInputStream("C:/Users/Саоша/IdeaProjects/Semestr/resources/db.properties"));
//
//            System.out.print(properties.getProperty("db.host"));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        UniversityService serv = ServiceFactory.getUniversityService();
//        Iterator<University> list = serv.getAllUnivWithSpec(8).iterator();

        ExtraPointsDAO dao = DAOFactory.getExtraPointsDAO();
//        Iterator<Achivement> list = dao.findAll().iterator();
//        System.out.println(list.next().getId());

        AdminService serv = ServiceFactory.getAdminService();

        System.out.println(serv.checkAdmin("admin", "R1987-2014"));



    }
}

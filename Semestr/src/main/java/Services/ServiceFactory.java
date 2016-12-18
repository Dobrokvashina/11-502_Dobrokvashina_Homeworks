package Services;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Саоша on 31.10.2016.
 */
public class ServiceFactory {

    private static Properties properties;


    static {

        properties = new Properties();

        try {

            properties.load(

                    new FileInputStream("C:\\Users\\Саоша\\IdeaProjects\\Semestr\\resources\\service.properties"));

        } catch (IOException e) {

            e.printStackTrace();

        }

    }


    public static UniversityService getUniversityService() {

        UniversityService universityService = null;

        try {

            universityService = (UniversityService) Class.forName(properties.getProperty("universityServ.class")).newInstance();

        } catch (InstantiationException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

        return universityService;

    }



    public static UserService getUserService() {

        UserService userService = null;

        try {

            userService = (UserService) Class.forName(properties.getProperty("userServ.class")).newInstance();

        } catch (InstantiationException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

        return userService;

    }


    public static AdminService getAdminService() {

        AdminService adminService = null;

        try {

            adminService = (AdminService) Class.forName(properties.getProperty("admServ.class")).newInstance();

        } catch (InstantiationException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

        return adminService;

    }
}
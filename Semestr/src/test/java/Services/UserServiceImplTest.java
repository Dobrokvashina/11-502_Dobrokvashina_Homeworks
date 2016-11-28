package Services;

import Classes.*;
import DAO.*;
import Utils.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import static org.mockito.Mockito.*;

/**
 * Created by Саоша on 28.11.2016.
 */
public class UserServiceImplTest {

    private UserServiceImpl userServ;
    private UsersDAO usersDAO;
    private UserAchiveDAO userAchiveDAO;
    private UserPointsDAO userPointsDAO;
    private SubjectsDAO subjectsDAO;
    private AchivementsDAO achivementsDAO;
    private Verifier verifier;

    private List<Achivement> achs;
    private User one;

    @Before
    public void setUp() throws Exception {

        usersDAO = mock(UsersDAO.class);
        userAchiveDAO = mock(UserAchiveDAO.class);
        userPointsDAO = mock(UserPointsDAO.class);
        subjectsDAO = mock(SubjectsDAO.class);
        achivementsDAO = mock(AchivementsDAO.class);
        verifier = mock(Verifier.class);

        when(verifier.existUser(1)).thenReturn(true);
        when(verifier.existUser("numOne")).thenReturn(true);

        one = new User(1, "Дмитрий", "Нагиев", "Россия", "Первый город", "numOne", MD5.getHash("imFirst"));
        when(usersDAO.find(1)).thenReturn(one);
        when(usersDAO.find("numOne")).thenReturn(one);

        when(verifier.existUPoints(4)).thenReturn(false);
        when(verifier.existUAchive(4)).thenReturn(true);

        int[][] extra = new int[2][2];
        extra[0][0] = 1;
        extra[1][0] = 1;
        extra[0][1] = 1;
        extra[1][1] = 11;
        when(userAchiveDAO.find(1)).thenReturn(extra);

        Subject sub = new Subject(1, "Первозначность");

        Achivement ach1 = new Achivement(sub,"Ведущий на ПЕРВОМ", 1);
        Achivement ach11 = new Achivement(sub,"Мы ПЕРВЫЕ!", 11);

        achs = new LinkedList<Achivement>();
        achs.add(ach1);
        achs.add(ach11);

        when(achivementsDAO.find(1)).thenReturn(ach1);
        when(achivementsDAO.find(11)).thenReturn(ach11);


        userServ = new UserServiceImpl(verifier, subjectsDAO,usersDAO,userPointsDAO,userAchiveDAO,achivementsDAO);
    }

    @Test
    public void getUser() throws Exception {
        User res = userServ.getUser(1);
        one.setAchivements(achs);

        Assert.assertEquals(res, one);
    }

    @Test
    public void checkPassword() throws Exception {
        Assert.assertFalse(userServ.checkPassword("nuOne", "imfirst"));
    }

}
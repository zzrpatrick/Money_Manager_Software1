package comp3350.moneymanager.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.moneymanager.application.Main;
import comp3350.moneymanager.application.Services;
import comp3350.moneymanager.business.AccessUsers;
import comp3350.moneymanager.objects.User;
import comp3350.moneymanager.tests.persistence.DataAccessStub;


public class AccessUsersTest extends TestCase
{
    private final AccessUsers accessUsers = new AccessUsers();
    private String dbName = Main.dbName;

    public AccessUsersTest(String arg0)
    {
        super(arg0);
    }

    public void testAccessUsers()
    {
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));

        System.out.println("Testing AccessUsers: Insert");

        String userName = "name1";
        User userA = new User(userName, 50, 20);

        try
        {
            accessUsers.insertUser(userA);
        } catch (Exception e)
        {
            fail("insertUser failed");
        }

        accessUsers.deleteUser(userA);

        System.out.println("Testing AccessUsers: GetRandom");
        assertNull(accessUsers.getRandom(userName));


        accessUsers.insertUser(userA);
        User userD = accessUsers.getRandom(userName);
        assertNotNull(userD);
        assertEquals(userD.getName(), userA.getName());
        assertEquals(userD.getMonthlyPay(), userA.getMonthlyPay());
        assertEquals(userD.getCurrMoney(), userA.getCurrMoney());

        System.out.println("Testing AccessUsers: Delete");
        accessUsers.deleteUser(userA);
        User userC = accessUsers.getRandom(userName);
        assertNull(userC);

        System.out.println("Testing AccessUsers: GetUserCount");
        accessUsers.insertUser(userA);
        assertEquals(accessUsers.getUserCount(), 5);
        accessUsers.deleteUser(userA);
        assertEquals(accessUsers.getUserCount(), 4);

        System.out.println("Testing AccessUsers: GetUsers");
        accessUsers.insertUser(userA);
        accessUsers.insertUser(new User("15"));
        ArrayList<User> userList = new ArrayList<>();
        accessUsers.getUsers(userList);
        assertEquals(userList.size(), 6);

        System.out.println("Testing AccessUsers: UpdateUsers");
        userA.setName("Jimbo");
        accessUsers.updateUser(userA);
        assertEquals(accessUsers.getRandom("Jimbo").getName(), "Jimbo");
        userA.setMonthlyPay(50000);
        accessUsers.updateUser(userA);
        assertEquals(accessUsers.getRandom("Jimbo").getMonthlyPay(), 50000);

        accessUsers.deleteUser(userA);
        assertFalse(accessUsers.containsUser(userA));

        Services.closeDataAccess();
    }
}


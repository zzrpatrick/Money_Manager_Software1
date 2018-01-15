package comp3350.moneymanager.tests.objects;

import junit.framework.TestCase;

import comp3350.moneymanager.application.Services;
import comp3350.moneymanager.objects.User;
import comp3350.moneymanager.tests.persistence.DataAccessStub;

public class UserTest extends TestCase
{
    private final User user;
    private final DataAccessStub database = new DataAccessStub("MM");

    public UserTest(String arg0)
    {
        super(arg0);
        Services.createDataAccess(database);
        Services.getDataAccess("MM");
        user = new User("name1", 100, 2);
    }

    public void testNullName()
    {
        System.out.println("Testing User: NullName");
        try
        {
            new User(null, 100, 2);
            fail("Expected NullPointerException");
        } catch (NullPointerException ignored) {}
    }

    public void testTwoConstructor()
    {
        System.out.println("Testing User: test constructor");
        User user1= new User("aa", 100, 10000);
        assertNotNull(user1);
        User user2 = new User("aa", 100, 10000);
        assertNotNull(user2);

        try
        {
            User user3 = new User(null, 100, 10000);
            fail("Expected NullPointerException");
        } catch(NullPointerException ignored){} //Could probably combine this with testNullName


    }

    public void testCalcMonthlyPay()
    {
        //TODO have to rewrite this
        System.out.println("Testing User: test calcMonthlyPay");
        User user1a= new User("aa", 1000, 10000);
        assertEquals(1000, user1a.getMonthlyPay());
        User user2a= new User("cc", 1000, 10000);
        assertEquals(1000, user2a.getMonthlyPay());
        User user3a= new User("cc", 1000, 10000);
        assertEquals(1000, user3a.getMonthlyPay());
        User user1b= new User("bb", 0, 10000);
        assertEquals(0, user1b.getMonthlyPay());
        User user2b= new User("dd", 0, 10000);
        assertEquals(0, user2b.getMonthlyPay());
        User user3b= new User("dd", 0, 10000);
        assertEquals(0, user3b.getMonthlyPay());
    }

    public void testCalcMonthlyNetPay()
    {
        System.out.println("Testing User: test calcMonthlyNetPay");
    }


    public void testEquals()
    {
        System.out.println("Testing User: testEqual");
        User user1= new User("aa", 1000, 10000);
        User user2= new User("aa", 1000, 10000);
        User user3= new User("bb", 1000, 10000);
        assertEquals(user1,user2);
        assertFalse(user1.equals(user3));
        assertFalse(user1.equals(null));
        assertFalse(user1.equals(1));
    }

    public void testSetters()
    {
        System.out.println("Testing User: setters");
        User user2= new User("aa", 1000, 10000);
        user2.updateCurrMoney(100000);
        assertEquals(110000, user2.getCurrMoney());
        user2.setMonthlyPay(10000);
        assertEquals(10000, user2.getMonthlyPay());
        user2.setName("bb");
        assertEquals("bb", user2.getName());
    }
}

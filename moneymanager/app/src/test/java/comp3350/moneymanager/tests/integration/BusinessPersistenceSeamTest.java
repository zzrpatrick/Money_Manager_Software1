package comp3350.moneymanager.tests.integration;

import junit.framework.TestCase;

import comp3350.moneymanager.application.Services;
import comp3350.moneymanager.application.Main;
import comp3350.moneymanager.business.AccessUsers;
import comp3350.moneymanager.business.AccessBills;
import comp3350.moneymanager.business.AccessTransactions;
import comp3350.moneymanager.objects.User;

public class BusinessPersistenceSeamTest extends TestCase
{
    public BusinessPersistenceSeamTest(String arg0)
    {
        super(arg0);
    }

    public void testAccessUsers()
    {
        AccessUsers au;
        User user;
        String result;

        Services.closeDataAccess();

        System.out.println("\nStarting Integration test of AccessUsers to persistence");

        Services.createDataAccess(Main.dbName);

        au = new AccessUsers();

        user = au.getRandom("BillyJoe");
        assertEquals("BillyJoe", user.getName());

        result = au.deleteUser(user);
        assertNull(result);
        assertNull(au.getRandom("BillyJoe"));

        result = au.insertUser(user);
        assertNull(result);
        user = au.getRandom("BillyJoe");
        assertEquals("BillyJoe", user.getName());

        user = au.getRandom("sammmmmmmmm");
        assertNull(user);

        System.out.println("\nStarting Integration test of AccessBills to persistence");
        //TODO add accessBills tests here. Must NOT change database. ie no insertions, deletions, or updates
        System.out.println("\nStarting Integration test of AccessTransactions to persistence");
        //TODO add accessTransactions tests here. Must NOT change database. ie no insertions, deletions, or updates

        Services.closeDataAccess();

        System.out.println("Finished Integration test of AccessStudents to persistence");
    }
}
package comp3350.moneymanager.tests.persistence;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.moneymanager.application.Main;
import comp3350.moneymanager.application.Services;
import comp3350.moneymanager.objects.Bill;
import comp3350.moneymanager.objects.Transaction;
import comp3350.moneymanager.objects.User;
import comp3350.moneymanager.persistence.DataAccess;

public class DataAccessTest extends TestCase
{
    private final static String dbName = Main.dbName;

    public DataAccessTest(String arg0)
    {
        super(arg0);
    }

    public void testDataAccess()
    {
        DataAccess dataAccess;

        Services.closeDataAccess();

        // Use the following statement to run with the stub database
        dataAccess = Services.createDataAccess(new DataAccessStub(dbName));

        System.out.println("\nTesting Database (stub)");
        dataAccessTest();

        System.out.println("Finished Persistence test DataAccess (using stub)");
    }

    public static void dataAccessTest()
    {
        DataAccess dataAccess;

        ArrayList<User> userList;
        ArrayList<Bill> billList;
        ArrayList<Transaction> transactionList;
        String result;

        User user;
        Bill bill;
        Transaction transaction;

        dataAccess = Services.getDataAccess(dbName);
        
		userList = new ArrayList<User>();
		result = dataAccess.getUserSequential(userList);
		assertNull(result);
		assertNotNull(userList);
		assertEquals(4, userList.size());
		user = userList.get(0);
		assertEquals("BillyJoe", user.getName());

		billList = new ArrayList<Bill>();
		result = dataAccess.getBillSequential("Gary Chalmers", billList);
		assertNull(result);
		assertNotNull(billList);
		assertEquals(2, billList.size());
		bill = billList.get(0);
		assertEquals("Car bill", bill.getName());


        transactionList = new ArrayList<Transaction>();
		result = dataAccess.getTransactionSequential("Joey Joerson", transactionList);
        assertNull(result);
        assertNotNull(transactionList);
        assertEquals(2, transactionList.size());
        transaction = transactionList.get(0);
        assertEquals("McDonalds purchase", transaction.getName());

        //TODO need more tests here

        Services.closeDataAccess();
    }
}

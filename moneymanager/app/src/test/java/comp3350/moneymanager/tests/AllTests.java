package comp3350.moneymanager.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import comp3350.moneymanager.application.Main;
import comp3350.moneymanager.application.Services;
import comp3350.moneymanager.tests.business.AccessBillsTest;
import comp3350.moneymanager.tests.business.AccessUsersTest;
import comp3350.moneymanager.tests.business.ValidateTest;
import comp3350.moneymanager.tests.objects.BillTest;
import comp3350.moneymanager.tests.objects.TransactionTest;
import comp3350.moneymanager.tests.objects.UserTest;
import comp3350.moneymanager.tests.persistence.DataAccessStub;
import comp3350.moneymanager.tests.persistence.DataAccessTest;

public class AllTests
{
    private static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("All tests");
        testObjects();
        testBusiness();
        testPersistence();
        return suite;
    }

    private static void testObjects()
    {
        suite.addTestSuite(BillTest.class);
        suite.addTestSuite(UserTest.class);
        suite.addTestSuite(TransactionTest.class);
    }

    private static void testPersistence()
    {
        suite.addTestSuite(DataAccessTest.class);
    }

    private static void testBusiness()
    {
        suite.addTestSuite(AccessUsersTest.class);
        suite.addTestSuite(AccessBillsTest.class);
        suite.addTestSuite(ValidateTest.class);
    }
}

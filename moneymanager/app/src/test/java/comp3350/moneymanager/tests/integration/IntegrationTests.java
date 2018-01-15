package comp3350.moneymanager.tests.integration;

import junit.framework.Test;
import junit.framework.TestSuite;

public class IntegrationTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Integration tests");
        suite.addTestSuite(BusinessPersistenceSeamTest.class);
        suite.addTestSuite(DataAccessHSQLDBTest.class);
        return suite;
    }
}

package comp3350.moneymanager.tests.objects;

import junit.framework.TestCase;

import comp3350.moneymanager.objects.Transaction;

public class TransactionTest extends TestCase
{
    public TransactionTest(String arg0)
    {
        super(arg0);
    }

    public void testUnnamedTransaction()

    {
        System.out.println("Testing Transaction: Unnamed Transaction");
        Transaction unnamedTransaction = new Transaction(50);

        assertNotNull(unnamedTransaction);
        assertEquals(unnamedTransaction.getName(), "Unknown");
        assertEquals(unnamedTransaction.getCost(), 50);
        unnamedTransaction.setName("bananas");
        assertEquals("bananas", unnamedTransaction.getName());
        unnamedTransaction.setCost(500);
        assertEquals(500, unnamedTransaction.getCost());
    }

    public void testNamedTransaction()
    {
        System.out.println("Testing Transaction: NamedTransaction");
        Transaction namedTransaction = new Transaction("0", "popcorn", 100);
        assertNotNull(namedTransaction);
        assertEquals("popcorn", namedTransaction.getName());
        assertEquals(100, namedTransaction.getCost());
        namedTransaction.setName("potatoes");
        assertEquals("potatoes", namedTransaction.getName());
        namedTransaction.setCost(1000);
        assertEquals(1000, namedTransaction.getCost());
    }

    public void testNullInput()
    {
        System.out.println("Testing Transaction: NullInput");
        try
        {
            new Transaction("0", null, 100);
            fail("Expected a NullPointerException");
        } catch (NullPointerException ignored)
        {
        }
    }


    public void testWrongTypeObjectEquals()
    {
        System.out.println("test wrong type of objects comparison");
        Transaction transTest = new Transaction("0", "hallo", 100);
        assertFalse(transTest.equals(100));
    }
}

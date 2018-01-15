package comp3350.moneymanager.tests.objects;

import junit.framework.TestCase;

import comp3350.moneymanager.objects.Bill;

public class BillTest extends TestCase
{
    public BillTest(String arg0)
    {
        super(arg0);
    }

    public void testConstructor()
    {
        System.out.println("Testing Bill: Constructor");

        String name = "Phone Bill";
        int cost = 40;
        Bill billA = new Bill("0", name, cost);

        assertNotNull(billA);
        assertEquals(name, billA.getName());
        assertEquals(cost, billA.getMonthlyCost());
    }

    public void testNullInput()
    {
        System.out.println("Testing Bill: Null Input");

        try
        {
            new Bill("whatever", null, 0);
            fail("Expected NullPointerException");
        } catch (NullPointerException ignored) {}
        //TODO this is repeated in testNullName
    }

    public void testEquals()
    {
        System.out.println("Testing Bill: Equals");

        int costA = 35;
        int costB = 2000;
        Bill billA = new Bill("0", "Phone payment", costA);
        Bill billB = new Bill("0", "Car payment", costB);

        assertFalse(billA.equals(billB));
        assertFalse(billA.equals(null));
        assertFalse(billA.equals(15));
        assertEquals(billA,billA);
        //TODO need more tests here
    }

    public void testNullName()
    {
        System.out.println("Testing Bill: null name");
        try
        {
            int costD = 150;
            new Bill("0", null, costD);
            fail("Expected a NullPointerException");
        } catch (NullPointerException ignored) {}
    }

}

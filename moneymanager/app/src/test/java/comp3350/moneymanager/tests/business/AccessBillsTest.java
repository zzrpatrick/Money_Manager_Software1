package comp3350.moneymanager.tests.business;


import junit.framework.TestCase;

import java.util.ArrayList;


import comp3350.moneymanager.application.Main;
import comp3350.moneymanager.application.Services;
import comp3350.moneymanager.business.AccessBills;
import comp3350.moneymanager.objects.Bill;
import comp3350.moneymanager.tests.persistence.DataAccessStub;


public class AccessBillsTest extends TestCase
{
    private final AccessBills accessBills = new AccessBills();
    private String dbName = Main.dbName;

    public AccessBillsTest(String arg0)
    {
        super(arg0);
    }

    public void testInsertBill()
    {
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));

        System.out.println("Testing AccessBills: Insert");

        Bill billA = new Bill("111", "whatever");

        String result = accessBills.insertBill(billA);
        assertTrue(accessBills.containsBill(billA));

        try
        {
            accessBills.insertBill(null);
            fail("Failed to return null exception");
        } catch (NullPointerException ignored) {}

        System.out.println("Testing AccessBills: GetRandom");

        assertNull(accessBills.getRandom("sams", "Joey Joerson"));
        accessBills.insertBill(new Bill("sams", "samboy"));
        assertNotNull(accessBills.getRandom("sams", "samboy"));

        System.out.println("Testing AccessBills: Contains");

        billA = new Bill("0", "Woozle wazzle");

        assertFalse(accessBills.containsBill(billA));
        accessBills.insertBill(billA);
        assertTrue(accessBills.containsBill(billA));
        accessBills.deleteBill(billA);
        assertFalse(accessBills.containsBill(billA));

        assertFalse(accessBills.containsBill(null));
        assertFalse(accessBills.containsBill(new Bill("0", "")));

        System.out.println("Testing AccessBills: Delete");

        billA = new Bill("15", "Car bill");
        accessBills.insertBill(billA);
        accessBills.deleteBill(billA);
        assertFalse(accessBills.containsBill(billA));

        try
        {
            accessBills.deleteBill(null);
        } catch (Exception e)
        {
            fail("calling deleteBill(null) fails.");
        }

        System.out.println("Testing AccessBills: GetBills");

        billA = new Bill("99", "whatever bill");

        ArrayList<Bill> tempBillList = new ArrayList<>();

        accessBills.getBills("99", tempBillList);
        assertTrue(tempBillList.isEmpty());

        accessBills.insertBill(billA);
        accessBills.getBills("99", tempBillList);
        assertFalse(tempBillList.isEmpty());

        accessBills.getBills("9999", tempBillList);
        assertTrue(tempBillList.isEmpty());

        Bill billB;
        String ownerName = "1234";
        String oldName = "sambill";
        int cost = 50;
        int newCost = 889;
        String newName = "sambill_2.0";

        billA = new Bill(ownerName, oldName, cost);

        accessBills.insertBill(billA);

        billA.setMonthlyCost(newCost);
        billA.setName(newName);

        accessBills.updateBill(billA, oldName);

        billB = accessBills.getRandom(ownerName, newName);
        assertEquals(billB.getName(), newName);
        assertEquals(billA.getMonthlyCost(), newCost);

        oldName = newName;
        newName = "";
        billA.setName(newName);

        accessBills.updateBill(billA, oldName);

        billB = accessBills.getRandom(ownerName, newName);
        assertEquals(billB.getName(), newName);

        Services.closeDataAccess();
    }
}

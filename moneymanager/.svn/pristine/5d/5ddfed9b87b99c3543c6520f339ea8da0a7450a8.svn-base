package comp3350.moneymanager.tests.business;


import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.moneymanager.business.Calculate;
import comp3350.moneymanager.objects.Bill;


public class CalculateTest extends TestCase
{
    ArrayList<Bill> billList;
    
    public CalculateTest(String arg0)
    {
        super(arg0);
    }

    public void testTotalBillsCost()
    {
        billList = new ArrayList<Bill>();

        assertEquals(Calculate.totalBillsCost(null), 0);

        String nameA = "9999";
        billList.add(new Bill(nameA, "eeeee", 100));
        billList.add(new Bill(nameA, "eeeeee", 100));
        billList.add(new Bill(nameA, "eeeeeee", 100));
        billList.add(new Bill(nameA, "eeeeeeee", 100));
        billList.add(new Bill(nameA, "eeeeeeeee", 100));
        assertEquals(100 * 5, Calculate.totalBillsCost(billList));

        billList.clear();

        String nameB = "77";
        billList.add(new Bill(nameB, "f", 0));
        billList.add(new Bill(nameB, "ff", 0));
        billList.add(new Bill(nameB, "fff", 0));
        assertEquals(Calculate.totalBillsCost(billList), 0);

        billList.clear();

        String nameC = "2223";
        billList.add(new Bill(nameC, "s", -55));
        billList.add(new Bill(nameC, "ss", 0));
        billList.add(new Bill(nameC, "sss", 110));
        billList.add(new Bill(nameC, "ssss", -2));
        assertEquals(Calculate.totalBillsCost(billList), 53);
    }
}
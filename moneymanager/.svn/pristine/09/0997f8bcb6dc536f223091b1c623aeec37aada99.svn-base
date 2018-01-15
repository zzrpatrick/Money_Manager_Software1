package comp3350.moneymanager.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.moneymanager.application.Main;
import comp3350.moneymanager.application.Services;
import comp3350.moneymanager.objects.Bill;
import comp3350.moneymanager.persistence.DataAccess;

public class AccessBills
{
    private final DataAccess dataAccess;

    public AccessBills()
    {
        dataAccess = Services.getDataAccess(Main.dbName);
    }

    public String insertBill(Bill bill)
    {
        return dataAccess.insertBill(bill);
    }

    public String deleteBill(Bill bill) { return dataAccess.deleteBill(bill); }

    public String getBills(String ownerName, List<Bill> billList)
    {
        billList.clear();
        return dataAccess.getBillSequential(ownerName, billList);
    }

    public int getBillCount(String ownerName)
    {
        return dataAccess.getBillCount(ownerName);
    }

    public String updateBill(Bill bill, String oldName) { return dataAccess.updateBill(bill, oldName); }

    public Bill getRandom(String ownerName, String name)
    {
        Bill bill = null;
        ArrayList<Bill> billList = dataAccess.getBillRandom(new Bill(ownerName, name));

        if (billList.size() == 1)
        {
            bill = billList.get(0);
        }

        return bill;
    }

    public boolean containsBill(Bill bill)
    {
        return dataAccess.containsBill(bill);
    }
}

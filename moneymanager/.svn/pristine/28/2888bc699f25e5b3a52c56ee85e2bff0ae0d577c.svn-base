package comp3350.moneymanager.business;

import java.util.ArrayList;

import comp3350.moneymanager.objects.Bill;

public class Calculate
{
    public static int totalBillsCost(ArrayList<Bill> billList)
    {
        int totalBillCost = 0;

        for (int i = 0; i < billList.size(); i++)
        {
            totalBillCost += billList.get(i).getMonthlyCost();
        }
        return totalBillCost;
    }
}

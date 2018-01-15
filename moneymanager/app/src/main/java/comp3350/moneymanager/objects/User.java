package comp3350.moneymanager.objects;

import java.util.ArrayList;

import comp3350.moneymanager.business.AccessUsers;

public class User
{
    private String name;
    private int currMoney;
    private int monthlyPay;

    public User(String newName)
    {
        name = newName;
        currMoney = 0;
        monthlyPay = 0;
    }

    public User(String newName, int newPay, int newCurrMoney)
    {
        if (newName == null)
            throw new NullPointerException("String newName cannot be null");

        name = newName;
        currMoney = newCurrMoney;
        monthlyPay = newPay;
    }

    public boolean equals(Object o)
    {
        if (o instanceof User)
        {
            if (name.equalsIgnoreCase(((User) o).getName()))
                return true;
        }
        return false;
    }

    public void updateCurrMoney(int newMoney) { currMoney += newMoney; }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getMonthlyPay() { return monthlyPay; }

    public void setMonthlyPay(int newPay){ monthlyPay = newPay; }

    public int getCurrMoney()
    {
        return currMoney;
    }
}

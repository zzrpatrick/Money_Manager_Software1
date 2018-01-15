package comp3350.moneymanager.objects;

public class Bill
{
    private String name;
    private int monthlyCost;
    private String ownerName;

    public Bill(String newOwnerName, String newName)
    {
        ownerName = newOwnerName;
        name = newName;
        monthlyCost = -1;
    }

    public Bill(String newOwnerName, String newName, int newMonthlyCost)
    {
        if (newName == null)
            throw new NullPointerException("String newName cannot be null");

        name = newName;
        monthlyCost = newMonthlyCost;
        ownerName = newOwnerName;
    }

    public Bill cloneX()
    {
        return new Bill(ownerName, name, monthlyCost);
    }

    public boolean equals(Object o)
    {
        if (o instanceof Bill)
        {
            Bill b = (Bill) o;
            if ((b.getOwnerName() == ownerName) && b.getName().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String newName) { name = newName; }

    public int getMonthlyCost()
    {
        return monthlyCost;
    }

    public void setMonthlyCost(int newCost) { monthlyCost = newCost; }

    public String getOwnerName() { return ownerName; }
}

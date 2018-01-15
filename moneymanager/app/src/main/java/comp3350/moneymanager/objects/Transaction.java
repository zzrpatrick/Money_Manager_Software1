package comp3350.moneymanager.objects;

public class Transaction
{
    private String name;
    private int cost;
    private String ownerName;

    public Transaction(int newCost)
    {
        name = "Unknown";
        cost = newCost;
    }

    public Transaction(String newOwnerName, String newName)
    {
        if (newName == null)
            throw new NullPointerException("newName cannot be null");

        ownerName = newOwnerName;
        name = newName;
    }

    public Transaction(String newOwnerName, String newName, int newCost)
    {
        if (newName == null)
            throw new NullPointerException("newName cannot be null");

        ownerName = newOwnerName;
        name = newName;
        cost = newCost;
    }

    public Transaction cloneX()
    {
        Transaction cloneTransaction = new Transaction(ownerName, name, cost);
        return cloneTransaction;
    }

    public boolean equals(Object o)
    {
        if (o instanceof Transaction)
        {
            Transaction t = (Transaction) o;
            if (t.getOwnerName().equalsIgnoreCase(ownerName) && t.getName().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }

    public String getOwnerName() { return ownerName; }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getCost()
    {
        return cost;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }
}

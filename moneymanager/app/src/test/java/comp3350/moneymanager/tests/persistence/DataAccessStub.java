package comp3350.moneymanager.tests.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.moneymanager.objects.Bill;
import comp3350.moneymanager.objects.Transaction;
import comp3350.moneymanager.objects.User;
import comp3350.moneymanager.persistence.DataAccess;

public class DataAccessStub implements DataAccess
{
    private final String dbName;
    private final String dbType = "stub";

    private final ArrayList<User> userList;
    private final ArrayList<Bill> billList;
    private final ArrayList<Transaction> transactionList;

    public DataAccessStub(String dbName)
    {
        this.dbName = dbName;
        userList = new ArrayList<>();
        billList = new ArrayList<>();
        transactionList = new ArrayList<>();
    }

    public void open(String dbName)
    {
        System.out.println("Opened " + dbType + " database " + dbName);

        User billyJoe = new User("BillyJoe",700,90);
        User gary = new User("Gary Chalmers", 55, 100);
        User joey = new User("Joey Joerson", 55, 100);
        User thrillho = new User("Thrillho", 688, 90);

        userList.add(billyJoe);
        userList.add(gary);
        userList.add(thrillho);
        userList.add(joey);

        billList.add(new Bill("Gary Chalmers", "Car bill", 500));
        billList.add(new Bill("Gary Chalmers", "Phone bill", 22));
        billList.add(new Bill("Joey Joerson", "Insurance", 100));
        billList.add(new Bill("Joey Joerson", "InsuranceB", 450));

        transactionList.add(new Transaction("Joey Joerson", "McDonalds purchase", 15));
        transactionList.add(new Transaction("Joey Joerson", "gum", 500));
        transactionList.add(new Transaction("Thrillho", "coffee", 1));
        transactionList.add(new Transaction("Thrillho", "monkey", 450));
    }

    public void close()
    {
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    public String insertUser(User user) throws NullPointerException
    {
        if (user == null)
            throw new NullPointerException("The new user cannot be null");

        if (!containsUser(user))
        {
            userList.add(user);
        }
        return null;
    }

    public boolean containsUser(User user)
    {
        return userList.contains(user);
    }

    public void deleteUser(String userName, int userID)
    {
        if (!userList.isEmpty() && containsUser(new User(userName)))
        {
            for (int i = 0; i < userList.size(); i++)
            {
                if (userList.get(i) != null)
                {
                    if (userList.get(i).getName().equals(userName))
                    {
                        userList.remove(i);
                    }
                }
            }
        }
    }

    public boolean containsBill(Bill bill)
    {
        boolean result = false;
        for (int i = 0; i < billList.size() && !result; i++)
        {
            if (billList.get(i).equals(bill))
                result = true;
        }

        return result;
    }

    public String updateUser(User person)
    {
        if (!userList.isEmpty())
        {
            for (int i = 0; i < userList.size(); i++)
            {
                if (userList.get(i).equals(person))
                {
                    userList.remove(i);
                    userList.add(person);
                }
            }
        }
        return null;
    }

    public ArrayList<User> getUserRandom(User user)
    {
        ArrayList<User> newUsers;
        int index;

        newUsers = new ArrayList<>();
        index = userList.indexOf(user);
        if (index >= 0)
        {
            newUsers.add(userList.get(index));
        }
        return newUsers;
    }

    public String deleteUser(User user)
    {
        int index;

        index = userList.indexOf(user);
        if (index >= 0)
        {
            userList.remove(index);
        }
        return null;
    }

    public String getUserSequential(List<User> userResult)
    {
        userResult.addAll(userList);
        return null;
    }

    public String getBillSequential(String ownerName, List<Bill> billResult)
    {
        Bill currBill;
        for (int i = 0; i < billList.size(); i++)
        {
            currBill = billList.get(i);
            if (currBill.getOwnerName() == ownerName)
            {
                billResult.add(currBill);
            }
        }
        return null;
    }

    public String insertBill(Bill bill) throws NullPointerException
    {
        if (bill == null)
            throw new NullPointerException("The new bill cannot be null");

        if (!containsBill(bill))
        {
            billList.add(bill);
        }
        return null;
    }

    public String deleteBill(Bill bill)
    {
        for (int i = 0; i < billList.size(); i++)
        {
            if (billList.get(i).equals(bill))
            {
                billList.remove(i);
            }
        }
        return null;
    }

    public ArrayList<Bill> getBillRandom(Bill bill)
    {
        Bill currBill;

        ArrayList<Bill> newBills = new ArrayList<>();

        for (int i = 0; i < billList.size(); i++)
        {
            currBill = billList.get(i);
            if (currBill.equals(bill))
                newBills.add(currBill);
        }
        return newBills;
    }

    public boolean noUsers()
    {
        return userList.isEmpty();
    }

    public int getUserCount() { return userList.size(); }

    public int getBillCount(String ownerName)
    {
        String currOwnerName;

        int count = 0;

        for (int i = 0; i < billList.size(); i++)
        {
            currOwnerName = billList.get(i).getOwnerName();
            if (currOwnerName == ownerName)
                count++;
        }
        return count;
    }

    public String updateBill(Bill bill, String oldName)
    {
        for (int i = 0; i < billList.size(); i++)
        {
            if (bill.equals(billList.get(i)))
            {
                billList.remove(i);
                billList.add(i, bill);
            }
        }
        return null;
    }

    public String getTransactionSequential(String ownerName, List<Transaction> transactionResult)
    {
        Transaction currTransaction;
        for (int i = 0; i < transactionList.size(); i++)
        {
            currTransaction = transactionList.get(i);
            if (currTransaction.getOwnerName() == ownerName)
            {
                transactionResult.add(currTransaction);
            }
        }
        return null;
    }

    public String insertTransaction(Transaction transaction) throws NullPointerException
    {
        if (transaction == null)
            throw new NullPointerException("The new transaction cannot be null");

        if (!containsTransaction(transaction))
        {
            transactionList.add(transaction);
        }
        return null;
    }

    public String deleteTransaction(Transaction transaction)
    {
        for (int i = 0; i < transactionList.size(); i++)
        {
            if (transactionList.get(i).equals(transaction))
            {
                transactionList.remove(i);
            }
        }
        return null;
    }

    public ArrayList<Transaction> getTransactionRandom(Transaction transaction)
    {
        Transaction currTransaction;

        ArrayList<Transaction> newTransactions = new ArrayList<>();

        for (int i = 0; i < transactionList.size(); i++)
        {
            currTransaction = transactionList.get(i);
            if (currTransaction.equals(transaction))
                newTransactions.add(currTransaction);
        }
        return newTransactions;
    }

    public int getTransactionCount(String ownerName)
    {
        String currOwnerName;

        int count = 0;

        for (int i = 0; i < transactionList.size(); i++)
        {
            currOwnerName = transactionList.get(i).getOwnerName();
            if (currOwnerName == ownerName)
                count++;
        }
        return count;
    }

    public String updateTransaction(Transaction transaction, String oldName)
    {
        for (int i = 0; i < transactionList.size(); i++)
        {
            if (transaction.equals(transactionList.get(i)))
            {
                transactionList.remove(i);
                transactionList.add(i, transaction);
            }
        }
        return null;
    }

    public boolean containsTransaction(Transaction transaction)
    {
        boolean result = false;
        for (int i = 0; i < billList.size() && !result; i++)
        {
            if (billList.get(i).equals(transaction))
                result = true;
        }

        return result;
    }
}

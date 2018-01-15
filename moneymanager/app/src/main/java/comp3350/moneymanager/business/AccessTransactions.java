package comp3350.moneymanager.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.moneymanager.application.Main;
import comp3350.moneymanager.application.Services;
import comp3350.moneymanager.objects.Transaction;
import comp3350.moneymanager.persistence.DataAccess;

public class AccessTransactions
{
    private final DataAccess dataAccess;

    public AccessTransactions()
    {
        dataAccess = Services.getDataAccess(Main.dbName);
    }

    public String insertTransaction(Transaction transaction)
    {
        return dataAccess.insertTransaction(transaction);
    }

    public String deleteTransaction(Transaction transaction) { return dataAccess.deleteTransaction(transaction); }

    public String getTransactions(String ownerName, List<Transaction> transactionList)
    {
        transactionList.clear();
        return dataAccess.getTransactionSequential(ownerName, transactionList);
    }

    public String updateTransaction(Transaction transaction, String oldName) { return dataAccess.updateTransaction(transaction, oldName); }

    public Transaction getRandom(String ownerName, String name)
    {
        Transaction transaction = null;
        ArrayList<Transaction> transactionList = dataAccess.getTransactionRandom(new Transaction(ownerName, name));

        if (transactionList.size() == 1)
        {
            transaction = transactionList.get(0);
        }

        return transaction;
    }

    public boolean containsTransaction(Transaction transaction)
    {
        return dataAccess.containsTransaction(transaction);
    }
}

package comp3350.moneymanager.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.moneymanager.objects.Bill;
import comp3350.moneymanager.objects.Transaction;
import comp3350.moneymanager.objects.User;

public interface DataAccess
{
    void open(String string);

    void close();

    String getUserSequential(List<User> userResult);

    ArrayList<User> getUserRandom(User user);

    String insertUser(User user);

    String insertBill(Bill bill);

    String getBillSequential(String ownerName, List<Bill> billResult);

    String updateUser(User user);

    String deleteUser(User user);

    String deleteBill(Bill bill);

    String updateBill(Bill bill, String oldName);

    int getBillCount(String ownerName);

    ArrayList<Bill> getBillRandom(Bill bill);

    boolean noUsers();

    int getUserCount();

    boolean containsUser(User user);

    boolean containsBill(Bill bill);

    String insertTransaction(Transaction transaction);

    String deleteTransaction(Transaction transaction);

    String getTransactionSequential(String ownerName, List<Transaction> transactionResult);

    String updateTransaction(Transaction transaction, String oldName);

    ArrayList<Transaction> getTransactionRandom(Transaction transaction);

    boolean containsTransaction(Transaction transaction);
}

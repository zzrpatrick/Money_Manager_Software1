package comp3350.moneymanager.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comp3350.moneymanager.objects.Bill;
import comp3350.moneymanager.objects.Transaction;
import comp3350.moneymanager.objects.User;

public class DataAccessObject implements DataAccess
{
    private final static String EOF = "  ";
    private final String dbName;
    private Statement st1;
    private Connection c1;
    private ResultSet rs2, rs3, rs4, rs5, rs6, rs7, rs8;
    private String dbType;
    private String cmdString;
    private int updateCount;
    private String result;

    public DataAccessObject(String dbName)
    {
        this.dbName = dbName;
    }

    public void open(String dbPath)
    {
        String url;
        try
        {
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
            c1 = DriverManager.getConnection(url, "SA", "");
            st1 = c1.createStatement();
        } catch (Exception e)
        {
            processSQLError(e);
        }
        System.out.println("Opened " + dbType + " database " + dbPath);
    }

    public void close()
    {
        try
        {
            cmdString = "shutdown compact";
            rs2 = st1.executeQuery(cmdString);
            c1.close();
        } catch (Exception e)
        {
            processSQLError(e);
        }
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    public boolean containsUser(User user)
    {
        ArrayList<User> userList = getUserRandom(user);
        return !userList.isEmpty();
    }

    public String getUserSequential(List<User> userResult)
    {
        User user;
        String myUserName;
        int myUserID, myPay, myCurrMoney;

        myUserName = EOF;

        result = null;
        try
        {
            cmdString = "Select * from Users";
            rs3 = st1.executeQuery(cmdString);
        } catch (Exception e)
        {
            processSQLError(e);
        }
        try
        {
            while (rs3.next())
            {
                myUserName = rs3.getString("Name");
                myPay = rs3.getInt("Pay");
                myCurrMoney = rs3.getInt("CurrMoney");
                user = new User(myUserName, myPay, myCurrMoney);
                userResult.add(user);
            }
            rs3.close();
        } catch (java.sql.SQLException e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    public ArrayList<User> getUserRandom(User user)
    {
        User resultUser;
        int myPay, myCurrMoney;
        String myUserName;

        myUserName = EOF;

        ArrayList<User> userList = new ArrayList<>();
        try
        {
            cmdString = "Select * from Users where name='"+user.getName()+"'";
            rs4 = st1.executeQuery(cmdString);
            while (rs4.next())
            {
                myUserName = rs4.getString("Name");
                myPay = rs4.getInt("Pay");
                myCurrMoney = rs4.getInt("CurrMoney");

                resultUser = new User(myUserName, myPay, myCurrMoney);
                userList.add(resultUser);
            }
            rs4.close();
        } catch (Exception e)
        {
            processSQLError(e);
        }
        return userList;
    }

    public String insertUser(User newUser)
    {
        String values;

        result = null;
        try
        {
            values = "'"+newUser.getName() +
                    "', " + newUser.getMonthlyPay() +
                    ", " + newUser.getCurrMoney();

            cmdString = "Insert into Users " + " Values(" + values + ")";
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String updateUser(User user)
    {
        String values;
        String where;

        result = null;
        try
        {
            values = "Name='" + user.getName() +
                    "', Pay=" + user.getMonthlyPay() +
                    ", CurrMoney=" + user.getCurrMoney();

            where = "where name='" + user.getName() +"'";
            cmdString = "Update Users " + "Set " + values + " " + where;
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String deleteUser(User user)
    {
        result = null;
        try
        {
            cmdString = "Delete from Users where name='" + user.getName() +"'";
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public boolean noUsers() { return getUserCount() == 0; }

    public int getUserCount()
    {
        String sCount = EOF;
        int count = 0;
        try
        {
            cmdString = "Select count(*) as userCount from Users";
            rs2 = st1.executeQuery(cmdString);
            if (rs2.next())
            {
                sCount = rs2.getString("userCount");
                count = Integer.parseInt(sCount);
            } else
            {
                System.out.println("No Data");
            }
        } catch (Exception e)
        {
            processSQLError(e);
        }
        return count;
    }

    public String updateBill(Bill bill, String oldName)
    {
        String values;
        String where;

        result = null;
        try
        {
            values = "Name='" + bill.getName() + "', Cost=" + bill.getMonthlyCost();

            where = "where OwnerName='" + bill.getOwnerName() + "' and name=" +"'"+oldName+"'";
            cmdString = "Update Bills " + " Set " + values + " " + where;
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String deleteBill(Bill bill)
    {
        result = null;
        String where;

        try
        {
            where = "where OwnerName='" + bill.getOwnerName() + "' and Name='" + bill.getName() + "'";
            cmdString = "Delete from Bills " + where;
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public int getBillCount(String ownerName)
    {
        String sCount = EOF;
        int count = 0;
        try
        {
            cmdString = "Select count(*) as billCount from Bills where ownerName='" + ownerName +"'";
            rs2 = st1.executeQuery(cmdString);
            if (rs2.next())
            {
                sCount = rs2.getString("billCount");
                count = Integer.parseInt(sCount);
            } else
            {
                System.out.println("No Data");
            }
        } catch (Exception e)
        {
            processSQLError(e);
        }
        return count;
    }

    public boolean containsBill(Bill bill)
    {
        ArrayList<Bill> billList = getBillRandom(bill);
        return !billList.isEmpty();
    }

    public String getBillSequential(String ownerName, List<Bill> billResult)
    {
        Bill bill;
        String myBillName;
        int myBillCost;

        result = null;
        try
        {
            cmdString = "Select * from Bills where ownerName='" +ownerName+"'";
            rs2 = st1.executeQuery(cmdString);
        } catch (Exception e)
        {
            processSQLError(e);
        }
        try
        {
            while (rs2.next())
            {
                myBillName = rs2.getString("Name");
                myBillCost = rs2.getInt("Cost");

                bill = new Bill(ownerName, myBillName, myBillCost);
                billResult.add(bill);
            }
            rs2.close();
        } catch (java.sql.SQLException e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    public ArrayList<Bill> getBillRandom(Bill bill)
    {
        Bill resultBill;
        int myCost;
        String myOwnerName, myBillName, where;

        myBillName = EOF;
        ArrayList<Bill> billList = new ArrayList<>();
        try
        {
            where = "OwnerName='" + bill.getOwnerName() + "' and Name='" + bill.getName() + "'";
            cmdString = "Select * from Bills where " + where;
            rs4 = st1.executeQuery(cmdString);
            while (rs4.next())
            {
                myOwnerName = rs4.getString("OwnerName");
                myBillName = rs4.getString("Name");
                myCost = rs4.getInt("Cost");

                resultBill = new Bill(myOwnerName, myBillName, myCost);
                billList.add(resultBill);
            }
            rs4.close();
        } catch (Exception e)
        {
            processSQLError(e);
        }
        return billList;
    }

    public String insertBill(Bill bill)
    {
        String values;

        result = null;
        try
        {
            values = "'" + bill.getOwnerName() + "', '" + bill.getName() + "', " + bill.getMonthlyCost();
            cmdString = "Insert into Bills " + " Values(" + values + ")";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String updateTransaction(Transaction transaction, String oldName)
    {
        String values;
        String where;

        result = null;
        try
        {
            values = "Name='" + transaction.getName() + "', Cost=" + transaction.getCost();

            where = "where ownerName='" + transaction.getOwnerName() + "' and name=" +"'"+oldName+"'";
            cmdString = "Update Transactions " + " Set " + values + " " + where;
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String deleteTransaction(Transaction transaction)
    {
        result = null;
        String where;

        try
        {
            where = "where ownerName='" + transaction.getOwnerName() + "' and Name='" + transaction.getName() + "'";
            cmdString = "Delete from Transactions " + where;
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public int getTransactionCount(String ownerName)
    {
        String sCount = EOF;
        int count = 0;
        try
        {
            cmdString = "Select count(*) as transactionCount from Transactions where ownerName='" + ownerName+"'";
            rs5 = st1.executeQuery(cmdString);
            if (rs5.next())
            {
                sCount = rs5.getString("transactionCount");
                count = Integer.parseInt(sCount);
            } else
            {
                System.out.println("No Data");
            }
        } catch (Exception e)
        {
            processSQLError(e);
        }
        return count;
    }

    public boolean containsTransaction(Transaction transaction)
    {
        ArrayList<Transaction> transactionList = getTransactionRandom(transaction);
        return !transactionList.isEmpty();
    }

    public String getTransactionSequential(String ownerName, List<Transaction> transactionResult)
    {
        Transaction transaction;
        String myTransactionName;
        int myTransactionCost;

        result = null;
        try
        {
            cmdString = "Select * from Transactions where ownerName='" + ownerName+"'";
            rs6 = st1.executeQuery(cmdString);
        } catch (Exception e)
        {
            processSQLError(e);
        }
        try
        {
            while (rs6.next() )
            {
                myTransactionName = rs6.getString("Name");
                myTransactionCost = rs6.getInt("Cost");

                transaction = new Transaction(ownerName, myTransactionName, myTransactionCost);
                transactionResult.add(transaction);
            }
            rs6.close();
        } catch (java.sql.SQLException e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    public ArrayList<Transaction> getTransactionRandom(Transaction transaction)
    {
        Transaction resultTransaction;
        String myOwnerName;
        int myCost;
        String myTransactionName, where;

        myTransactionName = EOF;
        ArrayList<Transaction> transactionList = new ArrayList<>();
        try
        {
            where = "OwnerName='" + transaction.getOwnerName() + "' and Name='" + transaction.getName() + "'";
            cmdString = "Select * from Transactions where " + where;
            rs7 = st1.executeQuery(cmdString);
            while (rs7.next())
            {
                myOwnerName = rs7.getString("OwnerName");
                myTransactionName = rs7.getString("Name");
                myCost = rs7.getInt("Cost");

                resultTransaction = new Transaction(myOwnerName, myTransactionName, myCost);
                transactionList.add(resultTransaction);
            }
            rs7.close();
        } catch (Exception e)
        {
            processSQLError(e);
        }
        return transactionList;
    }

    public String insertTransaction(Transaction transaction)
    {
        String values;

        result = null;
        try
        {
            values = "'"+transaction.getOwnerName() + "', '" + transaction.getName() + "', " + transaction.getCost();
            cmdString = "Insert into Transactions " + " Values(" + values + ")";
            System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    private String checkWarning(Statement st, int updateCount)
    {
        String result;

        result = null;
        try
        {
            SQLWarning warning = st.getWarnings();
            if (warning != null)
            {
                result = warning.getMessage();
            }
        } catch (Exception e)
        {
            result = processSQLError(e);
        }
        if (updateCount != 1)
        {
            result = "Tuple not inserted correctly.";
        }
        return result;
    }

    private String processSQLError(Exception e)
    {
        String result = "*** SQL Error: " + e.getMessage();
        e.printStackTrace();

        return result;
    }
}

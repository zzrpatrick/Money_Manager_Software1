package comp3350.moneymanager.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.moneymanager.application.Main;
import comp3350.moneymanager.application.Services;
import comp3350.moneymanager.objects.User;
import comp3350.moneymanager.persistence.DataAccess;

public class AccessUsers
{
    private final DataAccess dataAccess;
    private ArrayList<User> userList;
    private User user;
    private int currentUser;

    public AccessUsers()
    {
        dataAccess = Services.getDataAccess(Main.dbName);
        userList = null;
        user = null;
    }

    public String insertUser(User user)
    {
        if (dataAccess == null)
        {
            System.out.println("database not created");
            System.exit(4);
        }

        return dataAccess.insertUser(user);
    }

    public String deleteUser(User user) { return dataAccess.deleteUser(user); }

    public User getRandom(String name)
    {
        user = null;

        userList = dataAccess.getUserRandom(new User(name));
        if (userList.size() == 1)
        {
            user = userList.get(0);
        }

        return user;
    }

    public int getUserCount()
    {
        if (dataAccess.noUsers())
        {
            return 0;
        } else
        {
            return dataAccess.getUserCount();
        }
    }

    public String getUsers(List<User> userList)
    {
        userList.clear();
        return dataAccess.getUserSequential(userList);
    }

    public String updateUser(User user)
    {
        return dataAccess.updateUser(user);
    }

    public boolean noUsers() { return getUserCount() == 0; }

    public User getSequential()
    {
        if (userList == null)
        {
            userList = new ArrayList<>();
            currentUser = 0;
        }
        if (currentUser < userList.size())
        {
            user = userList.get(currentUser);
            currentUser++;
        } else
        {
            userList = null;
            user = null;
            currentUser = 0;
        }
        return user;
    }

    public boolean containsUser(User user)
    {
        return dataAccess.containsUser(user);
    }
}

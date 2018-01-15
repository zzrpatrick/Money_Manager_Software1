package comp3350.moneymanager.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import comp3350.moneymanager.R;
import comp3350.moneymanager.business.AccessUsers;
import comp3350.moneymanager.objects.User;

public class UserSelectActivity extends Activity
{

    private final AccessUsers accessUsers = new AccessUsers();
    private final ArrayList<User> userList = new ArrayList<>();
    private int positionPass;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        accessUsers.getUsers(userList);
        setContentView(R.layout.activity_user_select);
        loadList();
        hideButton();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        accessUsers.getUsers(userList);
        loadList();
        hideButton();
    }

    private void hideButton()
    {
        Button next = (Button) findViewById(R.id.ausSelectText);
        next.setVisibility(View.INVISIBLE);
    }

    private void loadList()
    {

        if (userList.size() > 0)
        {

            String nameList[] = new String[userList.size()];

            for (int i = 0; i < userList.size(); i++)
            {
                nameList[i] = userList.get(i).getName();
            }
            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout
                    .simple_list_item_1, nameList);
            final ListView listView = (ListView) findViewById(R.id.ausList);
            listView.setAdapter(itemsAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    positionPass = position;
                    Button next = (Button) findViewById(R.id.ausSelectText);
                    next.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    public void selectClick(View v)
    {
        HomeActivity.activeUser = userList.get(positionPass);
        finish();
        Intent UserHomeIntent = new Intent(UserSelectActivity.this, UserHomeActivity.class);
        UserSelectActivity.this.startActivity(UserHomeIntent);
    }
}

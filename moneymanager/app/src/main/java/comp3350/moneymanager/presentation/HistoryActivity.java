package comp3350.moneymanager.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import comp3350.moneymanager.R;
import comp3350.moneymanager.business.AccessTransactions;
import comp3350.moneymanager.objects.Transaction;

public class HistoryActivity extends Activity
{
    private AccessTransactions accessTransactions;
    int currentPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        accessTransactions = new AccessTransactions();
        loadDetails();
    }

    private void loadDetails()
    {
        loadList();
        clearTransactionDetails();
        Button deleteButton = (Button)findViewById(R.id.hDeleteButton);
        deleteButton.setVisibility(View.INVISIBLE);
        currentPosition = -1;
    }
    private void loadList()
    {
        ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
        accessTransactions.getTransactions(HomeActivity.activeUser.getName(), transactionList);
        if (transactionList == null || transactionList.size() <= 0)
        {
            String emptyList[] = new String[0];
            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout
                    .simple_list_item_1, emptyList);
            final ListView listView = (ListView) findViewById(R.id.hListView);
            listView.setAdapter(itemsAdapter);
        }
        else
        {
            String nameList[] = new String[transactionList.size()];

            for (int i = 0; i < transactionList.size(); i++)
            {
                nameList[i] = transactionList.get(i).getName();
            }
            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout
                    .simple_list_item_1, nameList);
            final ListView listView = (ListView) findViewById(R.id.hListView);
            listView.setAdapter(itemsAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
                    accessTransactions.getTransactions(HomeActivity.activeUser.getName(), transactionList);
                    Transaction clicked = transactionList.get(position);
                    currentPosition = position;
                    loadTransactionDetails(clicked);
                    Button deleteButton = (Button)findViewById(R.id.hDeleteButton);
                    deleteButton.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    private void loadTransactionDetails(Transaction current)
    {
        TextView details = (TextView) findViewById(R.id.hDetails);
        details.setText(current.getName() + "\nCost: $" + current.getCost() + "\nTransaction Name: " + current.getName());
    }

    private void clearTransactionDetails(){
        TextView details = (TextView)findViewById(R.id.hDetails);
        details.setText("");
    }

    public void deleteClick(View v)
    {
        ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
        accessTransactions.getTransactions(HomeActivity.activeUser.getName(), transactionList);
        Transaction currentTransaction = transactionList.get(currentPosition);
        accessTransactions.deleteTransaction(currentTransaction);
        loadDetails();
    }
}

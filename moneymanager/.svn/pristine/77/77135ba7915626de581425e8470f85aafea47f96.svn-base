package comp3350.moneymanager.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.moneymanager.R;
import comp3350.moneymanager.business.AccessBills;
import comp3350.moneymanager.objects.Bill;
import comp3350.moneymanager.objects.User;

public class BillListActivity extends Activity
{
    private int positionPass;
    private AccessBills accessBills;
    private Bill clickedBill;
    private User activeUser;
    private ArrayList<Bill> billList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_list);
        Button edit = (Button) findViewById(R.id.blEditButton);
        edit.setVisibility(View.INVISIBLE);
        activeUser = HomeActivity.activeUser;
        accessBills = new AccessBills();
        loadList();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Button edit = (Button) findViewById(R.id.blEditButton);
        edit.setVisibility(View.INVISIBLE);
        loadList();
        clearDetails();
    }

    private void loadList()
    {
        billList = new ArrayList<>();
        accessBills.getBills(activeUser.getName(), billList);

        if (billList == null || billList.size() <= 0)
        {
            TextView noBills = (TextView) findViewById(R.id.blNoBillsWarning);
            noBills.setVisibility(View.VISIBLE);

            String emptyList[] = new String[0];
            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout
                    .simple_list_item_1, emptyList);
            final ListView listView = (ListView) findViewById(R.id.blView);
            listView.setAdapter(itemsAdapter);
        } else
        {
            TextView noBills = (TextView) findViewById(R.id.blNoBillsWarning);
            noBills.setVisibility(View.INVISIBLE);
            String nameList[] = new String[billList.size()];

            for (int i = 0; i < billList.size(); i++)
            {
                Bill curr = billList.get(i);
                nameList[i] = curr.getName();
            }
            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout
                    .simple_list_item_1, nameList);
            final ListView listView = (ListView) findViewById(R.id.blView);
            listView.setAdapter(itemsAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    billList = new ArrayList<>();
                    accessBills.getBills(activeUser.getName(), billList);

                    if (billList != null && !billList.isEmpty())
                    {
                        clickedBill = billList.get(position);
                        positionPass = position;

                        Button edit = (Button) findViewById(R.id.blEditButton);
                        edit.setVisibility(View.VISIBLE);

                        loadDetails();
                    }
                }
            });
        }
    }

    private void loadDetails()
    {
        TextView details = (TextView) findViewById(R.id.blDetails);
        details.setText(clickedBill.getName() +
                "\nCost: $" + clickedBill.getMonthlyCost() +
                "\nMonthly Cost: $" + clickedBill.getMonthlyCost());
    }

    private void clearDetails()
    {
        TextView details = (TextView) findViewById(R.id.blDetails);
        details.setText("");
    }

    public void editClick(View V)
    {
        Intent EditIntent = new Intent(BillListActivity.this, BillActivity.class);
        EditIntent.putExtra("position", positionPass);
        BillListActivity.this.startActivity(EditIntent);
        loadList();
    }

    public void newBillClick(View V)
    {
        Intent NewBillIntent = new Intent(BillListActivity.this, NewBillActivity.class);
        BillListActivity.this.startActivity(NewBillIntent);
    }
}

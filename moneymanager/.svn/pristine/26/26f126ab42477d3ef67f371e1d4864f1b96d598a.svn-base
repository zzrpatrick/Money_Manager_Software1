package comp3350.moneymanager.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import comp3350.moneymanager.R;
import comp3350.moneymanager.business.AccessTransactions;
import comp3350.moneymanager.business.AccessUsers;
import comp3350.moneymanager.business.Validate;
import comp3350.moneymanager.objects.Transaction;

public class TransactionActivity extends Activity
{
    private AccessTransactions accessTransactions;
    private AccessUsers accessUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        TextView error = (TextView) findViewById(R.id.amountError);
        error.setVisibility(View.INVISIBLE);
        TextView error2 = (TextView) findViewById(R.id.nameError);
        error2.setVisibility(View.INVISIBLE);
        TextView error3 = (TextView) findViewById(R.id.existsError);
        error3.setVisibility(View.INVISIBLE);
        accessTransactions = new AccessTransactions();
        accessUsers = new AccessUsers();
    }

    public void transSaveClick(View v)
    {
        String ownerName = HomeActivity.activeUser.getName();
        int newCost = 0;

        EditText name = (EditText) findViewById(R.id.atTransName);
        String checkName = name.getText().toString();

        EditText cost = (EditText) findViewById(R.id.atTransAmount);
        String costString = cost.getText().toString();

        if(costString.equals(""))
        {
            costString = "0";
        }
        if(!Validate.checkValidInt(costString))
        {
            TextView error = (TextView) findViewById(R.id.amountError);
            error.setVisibility(View.VISIBLE);
        }
        else if (!Validate.checkValidName(checkName))
        {
            TextView error2 = (TextView) findViewById(R.id.nameError);
            error2.setVisibility(View.VISIBLE);
        }
        else if (accessTransactions.containsTransaction(new Transaction(ownerName, checkName)))
        {
            TextView error3 = (TextView) findViewById(R.id.existsError);
            error3.setVisibility(View.VISIBLE);
        }
        else
        {
            newCost = Integer.parseInt(costString);
            accessTransactions.insertTransaction(new Transaction(ownerName, checkName, newCost));
            HomeActivity.activeUser.updateCurrMoney(0-newCost);
            accessUsers.updateUser(HomeActivity.activeUser);
            finish();
        }
    }
}

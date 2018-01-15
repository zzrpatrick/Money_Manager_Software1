package comp3350.moneymanager.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import comp3350.moneymanager.R;
import comp3350.moneymanager.business.AccessBills;
import comp3350.moneymanager.business.Validate;
import comp3350.moneymanager.objects.Bill;

public class NewBillActivity extends Activity
{
    private AccessBills accessBills;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bill);
        accessBills = new AccessBills();
        loadDetails();
    }

    private void loadDetails()
    {
        TextView error = (TextView) findViewById(R.id.nbMissingFieldsWarning);
        error.setVisibility(View.INVISIBLE);
        TextView billExistsError = (TextView) findViewById(R.id.billExists);
        billExistsError.setVisibility(View.INVISIBLE);
    }

    public void newSaveClick(View v)
    {
        boolean validName = true;
        boolean validCost = true;
        EditText editName = (EditText) findViewById(R.id.nbBillName);
        TextView editCost = (EditText) findViewById(R.id.nbCostOut);

        String newName;
        int newMonthlyCost = 0;
        int newCost;

        newName = editName.getText().toString();
        String newCostString = editCost.getText().toString();

        if (newCostString.equals(""))
            newCostString = "0";

        validName = Validate.checkValidName(newName);
        validCost = Validate.checkValidInt(newCostString);

        if (validName && validCost)
        {
            newMonthlyCost = Integer.parseInt(newCostString);

            Bill newBill = new Bill(HomeActivity.activeUser.getName(), newName, newMonthlyCost);
            if (accessBills.containsBill(newBill))
            {
                TextView billExistsError = (TextView) findViewById(R.id.billExists);
                billExistsError.setVisibility(View.VISIBLE);
            } else
            {
                accessBills.insertBill(newBill);
                finish();
            }
        } else
        {
            TextView error = (TextView) findViewById(R.id.nbMissingFieldsWarning);
            error.setVisibility(View.VISIBLE);
        }
    }


    public void cancelClick(View v)
    {
        finish();
    }
}

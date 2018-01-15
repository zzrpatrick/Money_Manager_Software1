package comp3350.moneymanager.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.moneymanager.R;
import comp3350.moneymanager.business.AccessBills;
import comp3350.moneymanager.business.Validate;
import comp3350.moneymanager.objects.Bill;
import comp3350.moneymanager.objects.User;

public class BillActivity extends Activity
{
    private AccessBills accessBills;
    private Bill activeBill;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        Bundle extras = getIntent().getExtras();

        int position = -1;
        if (extras != null)
        {
            position = extras.getInt("position");
        }

        User activeUser = HomeActivity.activeUser;
        accessBills = new AccessBills();
        ArrayList<Bill> billList = new ArrayList<>();
        accessBills.getBills(activeUser.getName(), billList);
        activeBill = billList.get(position);
        loadDetails();
    }


    private void loadDetails()
    {
        TextView name = (TextView) findViewById(R.id.billTitlePrint);
        EditText newName = (EditText) findViewById(R.id.billName);
        TextView cost = (TextView) findViewById(R.id.billCostPrint);
        TextView newCost = (EditText) findViewById(R.id.billCost);
        TextView cycle = (TextView) findViewById(R.id.billCyclePrint);
        TextView error = (TextView) findViewById(R.id.billError);
        error.setVisibility(View.INVISIBLE);

        name.setText(activeBill.getName());
        newName.setText(activeBill.getName());

        cost.setText("$" + String.valueOf(activeBill.getMonthlyCost()));
        newCost.setText(String.valueOf(activeBill.getMonthlyCost()));
    }

    public void saveClick(View v)
    {
        String oldName;
        boolean validInput;
        String newName;
        String newCostString;
        int newCost = 0;

        EditText editName = (EditText) findViewById(R.id.billName);
        TextView editCost = (EditText) findViewById(R.id.billCost);


        Editable editNameText = editName.getText();
        newName = editNameText.toString();
        CharSequence editCostSeq = editCost.getText();
        newCostString = editCostSeq.toString();

        if (Validate.checkValidName(newName) && Validate.checkValidInt(newCostString))
        {
            newCost = Integer.parseInt(newCostString);
            oldName = activeBill.getName();
            activeBill.setName(newName);
            activeBill.setMonthlyCost(newCost);
            accessBills.updateBill(activeBill, oldName);
            finish();
        } else
        {
            TextView error = (TextView) findViewById(R.id.billError);
            error.setVisibility(View.VISIBLE);
        }
    }

    public void deleteClick(View v)
    {
        accessBills.deleteBill(activeBill);
        finish();
    }
}
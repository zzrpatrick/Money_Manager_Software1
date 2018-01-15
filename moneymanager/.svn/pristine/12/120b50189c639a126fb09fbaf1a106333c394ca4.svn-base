package comp3350.moneymanager.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.moneymanager.R;
import comp3350.moneymanager.business.AccessBills;
import comp3350.moneymanager.business.Calculate;
import comp3350.moneymanager.objects.Bill;
import comp3350.moneymanager.objects.User;

public class UserHomeActivity extends Activity
{

    private AccessBills accessBills;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        accessBills = new AccessBills();
        loadInfo();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        loadInfo();
    }

    private void loadInfo()
    {
        User activeUser = HomeActivity.activeUser;
        String userName = activeUser.getName();
        int monthlyPay = activeUser.getMonthlyPay();

        ArrayList<Bill> billList = new ArrayList<Bill>();
        accessBills.getBills(userName, billList);
        int totalBillsCost = Calculate.totalBillsCost(billList);

        TextView name = (TextView) findViewById(R.id.auhUsernamePrint);
        name.setText(userName);

        TextView income = (TextView) findViewById(R.id.auhMonthIncomePrint);
        income.setText("Monthly Income: $" + monthlyPay);

        TextView billsCost = (TextView) findViewById(R.id.auhTotalBillsCost);
        billsCost.setText("Total cost of bills: $" + totalBillsCost);

        TextView netIncome = (TextView) findViewById(R.id.auhMonthNetIncPrint);
        netIncome.setText("Net Monthly Income: $" + (monthlyPay-totalBillsCost));

        TextView savings = (TextView) findViewById(R.id.auhSavingsPrint);
        savings.setText("Savings: $" + Integer.toString(activeUser.getCurrMoney()));
    }

    public void billListClick(View V)
    {
        Intent BillListIntent = new Intent(UserHomeActivity.this, BillListActivity.class);
        UserHomeActivity.this.startActivity(BillListIntent);
    }

    public void historyClick(View V)
    {
        Intent HistoryIntent = new Intent(UserHomeActivity.this, HistoryActivity.class);
        UserHomeActivity.this.startActivity(HistoryIntent);
    }

    public void settingsClick(View V)
    {
        Intent SettingsIntent = new Intent(UserHomeActivity.this, SettingsActivity.class);
        UserHomeActivity.this.startActivity(SettingsIntent);
    }

    public void addClick(View V)
    {
        Intent AddIntent = new Intent(UserHomeActivity.this, TransactionActivity.class);
        UserHomeActivity.this.startActivity(AddIntent);
    }

    public void homeClick(View V)
    {
        Intent HomeIntent = new Intent(UserHomeActivity.this, HomeActivity.class);
        UserHomeActivity.this.startActivity(HomeIntent);
    }
}
//TODO when you do homeActivity -> next -> selectUserActivity -> userHomeActivity, it goes to the home page, it should go to userSelectActivity
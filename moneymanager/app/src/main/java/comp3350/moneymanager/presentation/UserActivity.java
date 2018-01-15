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

public class UserActivity extends Activity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user);

        User activeUser = HomeActivity.activeUser;
        String userName = activeUser.getName();
        AccessBills accessBills = new AccessBills();

        int monthlyPay = activeUser.getMonthlyPay();

        TextView name = (TextView) findViewById(R.id.auNamePrint);
        TextView pay = (TextView) findViewById(R.id.auMonthlyPayPrint);

        name.setText(userName);
        pay.setText("Monthly Income: $" + monthlyPay);
    }

    public void goBack(View v)
    {
        Intent BackIntent = new Intent(UserActivity.this, HomeActivity.class);
        UserActivity.this.startActivity(BackIntent);
    }

    public void nextClick(View V)
    {
        finish();
        Intent HomeIntent = new Intent(UserActivity.this, UserHomeActivity.class);
        UserActivity.this.startActivity(HomeIntent);
    }
}

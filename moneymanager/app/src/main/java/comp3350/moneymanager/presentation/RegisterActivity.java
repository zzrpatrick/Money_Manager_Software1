package comp3350.moneymanager.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import comp3350.moneymanager.R;
import comp3350.moneymanager.business.AccessUsers;
import comp3350.moneymanager.business.Validate;
import comp3350.moneymanager.objects.User;

public class RegisterActivity extends Activity
{
    private AccessUsers accessUsers;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        accessUsers = new AccessUsers();

        setContentView(R.layout.activity_start_info);
        TextView error = (TextView) findViewById(R.id.startEmptyFieldWarning);
        error.setVisibility(View.INVISIBLE);
        TextView error2 = (TextView) findViewById(R.id.auNetPrint);
        error2.setVisibility(View.INVISIBLE);
        TextView error3 = (TextView) findViewById(R.id.userExistsError);
        error3.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void clickBack(View v)
    {
        Intent RegisterIntent = new Intent(RegisterActivity.this, HomeActivity.class);
        RegisterActivity.this.startActivity(RegisterIntent);
    }

    public void nameClick(View V)
    {
        EditText nameText = (EditText) findViewById(R.id.startNewName);

        if (nameText.getText().toString().equals("Enter name here"))
        {
            nameText.setText("");
        }
    }

    public void incomeClick(View V)
    {
        EditText incomeText = (EditText) findViewById(R.id.startCurrIncome);

        if (incomeText.getText().toString().equals("0"))
        {
            incomeText.setText("");
        }
    }

    public void savingsClick(View V)
    {
        EditText savingsTextBox = (EditText) findViewById(R.id.startCurrSavings);
        Editable savingsText = savingsTextBox.getText();
        String savingsString = savingsText.toString();
        if (savingsString.equals("0"))
        {
            savingsTextBox.setText("");
        }
    }

    public void submitClick(View V)
    {
        EditText nameText = (EditText) findViewById(R.id.startNewName);
        EditText incomeText = (EditText) findViewById(R.id.startCurrIncome);
        EditText currMoneyText = (EditText) findViewById(R.id.startCurrSavings);

        String name = nameText.getText().toString();
        String monthlyPay = incomeText.getText().toString();
        String currMoney = currMoneyText.getText().toString();

        if (monthlyPay.equals(""))
            monthlyPay = "0";
        if (currMoney.equals(""))
            currMoney = "0";

        if (!Validate.checkValidName(name) || name.equals("Enter name here") || !Validate
                .checkValidInt(monthlyPay) || !Validate.checkValidInt(currMoney))
        {
            TextView error = (TextView) findViewById(R.id.startEmptyFieldWarning);
            error.setVisibility(View.VISIBLE);
        }
        else if (accessUsers.containsUser(new User(name)))
        {
            TextView error = (TextView) findViewById(R.id.userExistsError);
            error.setVisibility(View.VISIBLE);
        }
        else
        {
            User newUser = new User(name, Integer.parseInt(monthlyPay), Integer.parseInt(currMoney));
            accessUsers.insertUser(newUser);
            HomeActivity.activeUser = newUser;
            finish();
            Intent UserIntent = new Intent(RegisterActivity.this, UserActivity.class);
            RegisterActivity.this.startActivity(UserIntent);
        }
    }
}

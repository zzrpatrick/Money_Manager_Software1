package comp3350.moneymanager.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import comp3350.moneymanager.R;
import comp3350.moneymanager.business.AccessUsers;
import comp3350.moneymanager.business.Validate;
import comp3350.moneymanager.objects.User;

public class EditIncomeActivity extends Activity
{
    private User activeUser; //TODO need a better way to move active user around. Public static variable activeUser in homeActivity is BAD!!
    private AccessUsers accessUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_income);
        activeUser = HomeActivity.activeUser;
        loadDetails();
    }

    private void loadDetails()
    {
        TextView oldIncomeBox = (TextView) findViewById(R.id.eiCurrIncomeOut);
        oldIncomeBox.setText("$" + activeUser.getMonthlyPay());
        accessUsers = new AccessUsers();
        TextView incomeError = (TextView) findViewById(R.id.incomeWarning);
        incomeError.setVisibility(View.INVISIBLE);
    }

    public void incomeSaveClick(View v)
    {
        EditText newPayBox = (EditText) findViewById(R.id.eiNewIncome);
        Editable newPayStringText = newPayBox.getText();
        String newPayString = newPayStringText.toString();

        if (Validate.checkValidInt(newPayString))
        {
            int newPay = Integer.parseInt(newPayString);
            activeUser.setMonthlyPay(newPay);
            accessUsers.updateUser(activeUser);
            finish();

            Intent SettingsIntent = new Intent(EditIncomeActivity.this, SettingsActivity.class);
            SettingsIntent.putExtra("flashUpdate", true);
            EditIncomeActivity.this.startActivity(SettingsIntent);

        } else
        {
            TextView incomeError = (TextView) findViewById(R.id.incomeWarning);
            incomeError.setVisibility(View.VISIBLE);
        }
    }
}

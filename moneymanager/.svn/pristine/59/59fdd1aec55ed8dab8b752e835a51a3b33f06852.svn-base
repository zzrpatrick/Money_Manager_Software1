package comp3350.moneymanager.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import comp3350.moneymanager.R;

public class SettingsActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        TextView updatedText = (TextView) findViewById(R.id.updatedUserText);

        Bundle extras = getIntent().getExtras();

        if (extras != null && extras.getBoolean("flashUpdate"))
        {
            updatedText.setVisibility(View.VISIBLE);
        } else
        {
            updatedText.setVisibility(View.INVISIBLE);
        }
    }

    private void loadDetails()
    {
        TextView updatedText = (TextView) findViewById(R.id.updatedUserText);
        updatedText.setVisibility(View.INVISIBLE);
    }

    public void editNameClick(View v)
    {
        loadDetails();
        finish();
        Intent EditNameIntent = new Intent(SettingsActivity.this, EditNameActivity.class);
        SettingsActivity.this.startActivity(EditNameIntent);
    }

    public void editIncomeClick(View v)
    {
        loadDetails();
        finish();
        Intent EditIncomeIntent = new Intent(SettingsActivity.this, EditIncomeActivity.class);
        SettingsActivity.this.startActivity(EditIncomeIntent);
    }

    public void homeClick(View V)
    {
        loadDetails();
        finish();
        Intent HomeIntent = new Intent(SettingsActivity.this, HomeActivity.class);
        SettingsActivity.this.startActivity(HomeIntent);
    }
}

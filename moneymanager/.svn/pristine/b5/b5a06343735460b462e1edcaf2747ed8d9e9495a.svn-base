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

public class EditNameActivity extends Activity
{

    private AccessUsers accessUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);
        accessUsers = new AccessUsers();
        loadDetails();
    }

    private void loadDetails()
    {
        TextView oldNameView = (TextView) findViewById(R.id.enCurrNameOut);
        String oldName = HomeActivity.activeUser.getName();
        oldNameView.setText(oldName);
        EditText newNameBox = (EditText) findViewById(R.id.enCurrName);
        newNameBox.setText(oldName);
        TextView error = (TextView) findViewById(R.id.enNameWarning);
        error.setVisibility(View.INVISIBLE);
    }

    public void nameSaveClick(View v)
    {
        EditText newNameBox = (EditText) findViewById(R.id.enCurrName);
        Editable newNameBoxText = newNameBox.getText();
        String newName = newNameBoxText.toString();

        if (Validate.checkValidName(newName))
        {
            HomeActivity.activeUser.setName(newName);
            accessUsers.updateUser(HomeActivity.activeUser);
            finish();
            Intent SettingsIntent = new Intent(EditNameActivity.this, SettingsActivity.class);
            SettingsIntent.putExtra("flashUpdate", true);
            EditNameActivity.this.startActivity(SettingsIntent);
        } else
        {
            TextView error = (TextView) findViewById(R.id.enNameWarning);
            error.setVisibility(View.VISIBLE);
        }
    }

    public void nameCancelClick(View v)
    {
        finish();
    }
}

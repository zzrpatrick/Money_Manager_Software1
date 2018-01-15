package comp3350.moneymanager.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import comp3350.moneymanager.R;
import comp3350.moneymanager.application.Main;
import comp3350.moneymanager.business.AccessUsers;
import comp3350.moneymanager.objects.User;

public class HomeActivity extends Activity
{

    public static User activeUser;
    private AccessUsers accessUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        copyDatabaseToDevice();
        Main.startUp();
        setContentView(R.layout.activity_home);
        activeUser = null;
        accessUsers = new AccessUsers();
        checkForUsers();
    }

    private void checkForUsers()
    {
        Button btn = (Button) findViewById(R.id.homeNextButton);
        TextView txt = (TextView) findViewById(R.id.homeSelectAccPrint);
        if (accessUsers.getUserCount() > 0)
        {
            btn.setVisibility(View.VISIBLE);
            btn.setEnabled(true);
            txt.setVisibility(View.VISIBLE);
        } else
        {
            btn.setEnabled(false);
            btn.setVisibility(View.GONE);
            txt.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        Main.shutDown();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        checkForUsers();
    }

    private void copyDatabaseToDevice()
    {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try
        {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++)
            {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.dbName);

        } catch (IOException ioe)
        {
            Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }

    private void copyAssetsToDirectory(String[] assets, File directory) throws IOException
    {
        AssetManager assetManager = getAssets();

        for (String asset : assets)
        {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];
            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists())
            {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1)
                {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void startClick(View v)
    {
        Intent RegisterIntent = new Intent(HomeActivity.this, RegisterActivity.class);
        HomeActivity.this.startActivity(RegisterIntent);
    }

    public void homeNextClick(View v)
    {
        Intent UserSelectIntent = new Intent(HomeActivity.this, UserSelectActivity.class);
        HomeActivity.this.startActivity(UserSelectIntent);
    }
}

package comp3350.moneymanager;


import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.TextView;

import com.robotium.solo.Solo;

import java.util.Set;

import comp3350.moneymanager.presentation.BillActivity;
import comp3350.moneymanager.presentation.BillListActivity;
import comp3350.moneymanager.presentation.EditIncomeActivity;
import comp3350.moneymanager.presentation.EditNameActivity;
import comp3350.moneymanager.presentation.HomeActivity;
import comp3350.moneymanager.presentation.NewBillActivity;
import comp3350.moneymanager.presentation.RegisterActivity;
import comp3350.moneymanager.presentation.SettingsActivity;
import comp3350.moneymanager.presentation.TransactionActivity;
import comp3350.moneymanager.presentation.UserActivity;
import comp3350.moneymanager.presentation.UserHomeActivity;
import comp3350.moneymanager.presentation.UserSelectActivity;

//EditText FirsteditText = (EditText) solo.getView(R.id.EditText01);
 //       solo.enterText(FirsteditText, String.valueOf("10"));
public class AcceptanceTest extends ActivityInstrumentationTestCase2<HomeActivity>{
    private Solo solo;

    public AcceptanceTest(){
        super(HomeActivity.class);
    }

    public void setUp() throws Exception{
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }

    public void testExistingUser() throws Exception{
        solo.assertCurrentActivity("wrong activity", HomeActivity.class);
        solo.clickOnButton("Next");
        solo.assertCurrentActivity("wrong activity", UserSelectActivity.class);
        solo.clickInList(2);
        solo.clickOnButton("select");
        solo.assertCurrentActivity("wrong activity", UserHomeActivity.class);
        boolean actual = solo.searchText("Gary Chalmers");
        boolean expected = true;
        assertEquals("Gary Chalmers not found", expected, actual);
    }

    public void testEditName() throws Exception{
        solo.assertCurrentActivity("wrong activity", HomeActivity.class);
        solo.clickOnButton("Next");
        solo.assertCurrentActivity("wrong activity", UserSelectActivity.class);
        solo.clickInList(1);
        solo.clickOnButton("select");
        solo.assertCurrentActivity("wrong activity", UserHomeActivity.class);
        solo.clickOnButton(3);
        solo.assertCurrentActivity("wrong activity", SettingsActivity.class);
        solo.clickOnButton(1);
        solo.assertCurrentActivity("wrong activity", EditNameActivity.class);
        solo.clearEditText(0);
        solo.enterText(0, "Yrag Sremlahc");
        solo.clickOnButton(0);
        solo.assertCurrentActivity("wrong activity", SettingsActivity.class);
        solo.goBack();
        solo.assertCurrentActivity("wrong activity", UserHomeActivity.class);
    }

    public void testBillCreation() throws Exception{
        solo.assertCurrentActivity("wrong activity", HomeActivity.class);
        solo.clickOnButton("Next");
        solo.assertCurrentActivity("wrong activity", UserSelectActivity.class);
        solo.clickInList(1);
        solo.clickOnButton("select");
        solo.assertCurrentActivity("wrong activity", UserHomeActivity.class);
        solo.clickOnButton(1);
        solo.assertCurrentActivity("wrong activity", BillListActivity.class);
        solo.clickOnButton("New Bill");
        solo.assertCurrentActivity("wrong activity", NewBillActivity.class);
        solo.enterText(0, "New Bill");
        solo.enterText(1, "1000");
        solo.clickOnButton("Save");
        solo.assertCurrentActivity("wrong activity", BillListActivity.class);
        solo.goBack();
        solo.assertCurrentActivity("wrong activity", UserHomeActivity.class);
    }

    public void testTransactionCreation() throws Exception{
        solo.assertCurrentActivity("wrong activity", HomeActivity.class);
        solo.clickOnButton("Next");
        solo.assertCurrentActivity("wrong activity", UserSelectActivity.class);
        solo.clickInList(1);
        solo.clickOnButton("select");
        solo.assertCurrentActivity("wrong activity", UserHomeActivity.class);
        solo.clickOnButton(4);
        solo.assertCurrentActivity("wrong activity", TransactionActivity.class);
        solo.enterText(0, "New transaction");
        solo.enterText(1, "10");
        solo.clickOnButton("Save");
    }

    public void testInvalidRegister() throws Exception{
        solo.assertCurrentActivity("wrong activity", HomeActivity.class);

        solo.clickOnButton("Start");

        solo.assertCurrentActivity("wrong activity", RegisterActivity.class);
        EditText newName = (EditText)solo.getCurrentActivity().findViewById(R.id.startNewName);
        EditText currIncome = (EditText)solo.getCurrentActivity().findViewById(R.id.startCurrIncome);
        EditText currSavings = (EditText)solo.getCurrentActivity().findViewById(R.id.startCurrSavings);
        solo.enterText(newName,"");
        solo.enterText(currIncome, "5000");
        solo.enterText(currSavings, "10000");

        solo.clickOnButton("Submit");

        solo.assertCurrentActivity("wrong activity", RegisterActivity.class);
    }

    public void testValidRegister() throws Exception{
        solo.assertCurrentActivity("wrong activity", HomeActivity.class);

        solo.clickOnButton("Start");

        solo.assertCurrentActivity("wrong activity", RegisterActivity.class);
        solo.enterText(0, "Kyle Zoom");
        solo.enterText(1, "5000");
        solo.enterText(2, "10000");


        solo.clickOnButton("Submit");

        solo.assertCurrentActivity("wrong activity", UserActivity.class);

        solo.clickOnButton("Next");

        solo.assertCurrentActivity("wrong activity", UserHomeActivity.class);
    }

    public void testSettings() throws Exception{
        solo.assertCurrentActivity("wrong activity", HomeActivity.class);
        solo.clickOnButton("Next");
        solo.assertCurrentActivity("wrong activity", UserSelectActivity.class);
        solo.clickInList(1);
        solo.clickOnButton("select");
        solo.assertCurrentActivity("wrong activity", UserHomeActivity.class);
        solo.clickOnButton(3);
        solo.assertCurrentActivity("wrong activity", SettingsActivity.class);
        solo.clickOnButton("Edit Name");
        solo.assertCurrentActivity("wrong activity", EditNameActivity.class);
        solo.clickOnButton("Save");
        solo.assertCurrentActivity("wrong activity", SettingsActivity.class);
        solo.clickOnButton("Edit Income");
        solo.assertCurrentActivity("wrong activity", EditIncomeActivity.class);
        solo.enterText(0, "155");
        solo.clickOnButton("Save");
        solo.assertCurrentActivity("wrong activity", SettingsActivity.class);
    }

    public void testEditBill() throws Exception {
        solo.assertCurrentActivity("wrong activity", HomeActivity.class);
        solo.clickOnButton("Next");
        solo.assertCurrentActivity("wrong activity", UserSelectActivity.class);
        solo.clickInList(1);
        solo.clickOnButton("select");
        solo.assertCurrentActivity("wrong activity", UserHomeActivity.class);
        solo.clickOnButton(1);
        solo.assertCurrentActivity("wrong activity", BillListActivity.class);
        solo.clickOnButton("New Bill");
        solo.assertCurrentActivity("wrong activity", NewBillActivity.class);
        solo.enterText(0, "New Bill2");
        solo.enterText(1, "1000");
        solo.clickOnButton("Save");
        solo.assertCurrentActivity("wrong activity", BillListActivity.class);
        solo.clickInList(1);
        solo.clickOnButton("Edit");
        solo.assertCurrentActivity("wrong activity", BillActivity.class);
        solo.clearEditText(0);
        solo.enterText(0, "edited name");
        solo.clearEditText(1);
        solo.enterText(1, "1234");
        solo.clickOnButton(0);
        solo.assertCurrentActivity("wrong activity", BillListActivity.class);
        solo.clickInList(1);
    }
}

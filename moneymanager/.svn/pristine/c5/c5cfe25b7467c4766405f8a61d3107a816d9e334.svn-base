package comp3350.moneymanager.tests.business;

import junit.framework.TestCase;

import comp3350.moneymanager.business.Validate;

public class ValidateTest extends TestCase
{
    public ValidateTest(String arg0)
    {
        super(arg0);
    }

    public void testCheckDefault()
    {
        System.out.println("Testing Validate: checkDefault Method");
        assertFalse(Validate.checkDefault(""));
        assertFalse(Validate.checkDefault("Enter name here"));
        assertTrue(Validate.checkDefault("a"));
        assertTrue(Validate.checkDefault("a b"));
        assertTrue(Validate.checkDefault("123"));
    }

    public void testNullInput()
    {
        System.out.println("Testing Validate: NullInput");
        try
        {
            Validate.checkDefault(null);
            fail("Expected a null pointer exception");
        } catch (NullPointerException ignored)
        {
        }
    }

    public void testValidateNumInput()
    {
        System.out.println("Testing Validate: testingValidateInt");
        //TODO actually write some tests
    }

    public void testValidateName()
    {
        System.out.println("Testing Validate: testing ValidateName");
        assertTrue(Validate.checkValidName("abc"));
        assertTrue(Validate.checkValidName("a b c"));
        assertFalse(Validate.checkValidName(" "));
        assertFalse(Validate.checkValidName(" a"));
        assertFalse(Validate.checkValidName(""));


    }
}

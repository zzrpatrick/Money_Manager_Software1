package comp3350.moneymanager.business;

public class Validate
{
    public static boolean checkDefault(String txt)
    {
        boolean result = true;
        if (txt.equals("") || txt.equals("Enter name here"))
        {
            result = false;
        }
        return result;
    }

    public static boolean checkValidInt(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }

    public static boolean checkValidName(String s)
    {
        boolean result = true;
        String testTrim = s.trim();
        if (testTrim.length() <= 0 || testTrim.length() > 20)
        {
            result = false;
        } else
        {
            if (s.charAt(0) == ' ')
            {
                result = false;
            }
        }
        return result;
    }
}

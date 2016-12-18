import com.sun.java.swing.plaf.windows.WindowsInternalFrameTitlePane;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Саоша on 15.12.2016.
 */

public class Checker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your email:");
        String line = sc.next();

        while (isCorrectEmail(line) == null) {
            System.out.println("Your email is not valid. Try again:");
            line = sc.next();
        }

        System.out.println("Your email is " + line + ". And it is belong to " + isCorrectEmail(line));
    }

    public static String isCorrectEmail(String email) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\.]+@"
                + "([A-Za-z0-9-\\.]+\\.[A-Za-z]+)$");
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }
}

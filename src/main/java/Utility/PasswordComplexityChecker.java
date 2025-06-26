package Utility;

import org.testng.Reporter;

import static Listeners.testNg_listeners_simple.PassMessege;
import static Listeners.testNg_listeners_simple.failledMessege;

public class PasswordComplexityChecker {


    public static boolean isPasswordComplex(String password,int minLength) {
        // Define criteria for password complexity

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        try {
            // Check minimum length
            if (password.length() < minLength) {
                return false;
            }

            // Check for uppercase, lowercase, digit, and special character
            for (char ch : password.toCharArray()) {
                if (Character.isUpperCase(ch)) {
                    hasUppercase = true;
                } else if (Character.isLowerCase(ch)) {
                    hasLowercase = true;
                } else if (Character.isDigit(ch)) {
                    hasDigit = true;
                } else if (isSpecialCharacter(ch)) {
                    hasSpecialChar = true;
                }
            }
            Reporter.log(new Object() {}.getClass().getEnclosingMethod().getName() + PassMessege + "|| Data: " + password);

            // Check if all criteria are met
            return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
        }catch (Exception e){
            Reporter.log(new Object() {}.getClass().getEnclosingMethod().getName() + failledMessege + "|| Data: " + password);

            throw  e;

        }

    }

    public static boolean isSpecialCharacter(char ch) {
        // Define special characters
        String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>/?";

        // Check if the character is a special character
        return specialCharacters.indexOf(ch) != -1;
    }
}

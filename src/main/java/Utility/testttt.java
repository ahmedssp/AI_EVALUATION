package Utility;

public class testttt {


    public static void main(String[] args) {
        String text = "This is a sample sentence to count tokens";

        // Split the string by whitespace to get tokens
        String[] tokens = text.split("\\s+");

        // Count the number of tokens
        int tokenCount = tokens.length;

        // Print the number of tokens
        System.out.println("Number of tokens: " + tokenCount);
    }
}

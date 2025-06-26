package DataMethod;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class data_random_bad_practice {
    private static String name1;
    private static String name2;
    private String name;
    private static int q1, q2;

    public static String VALID_Generate_name() {
        name1 = new Faker().internet().domainName().replace(".", "");
        name2 = name1.split("")[0].toUpperCase();
        return name2 + name1;
    }

    public static String VALID_Generate_phone() {
        q1 = 10000 + (int) (new Random().nextFloat() * 9999);
        q2 = 1000 + (int) (new Random().nextFloat() * 9999);
        return "01" + (q1) + (q2);
    }

    public static String Numerical_Generate_ID(int NumberOfDigits) {

        return new Faker().number().digits(NumberOfDigits);
    }

    public static String inVALID_Generate_name() {
        name1 = new Faker().internet().domainName().replace(".", "");
        return name1;
    }

    public static String INVALID_Generate_phone() {
        q1 = 1000 + (int) (new Random().nextFloat() * 899);
        q2 = 100000 + (int) (new Random().nextFloat() * 899900);
        return String.valueOf(q1) + String.valueOf(q2);
    }

    public static String promotionCode(int digits) {
        return StringUtils.join(new Faker().resolve("commerce.promotion_code.adjective"),
                new Faker().resolve("commerce.promotion_code.noun"),
                new Faker().number().digits(digits));
    }

    public static String ValidEmail_Generater() {

        return new Faker().internet().emailAddress();
    }

    public static String INValidEmail_Generater() {

        return new Faker().internet().emailAddress().replace("@", "");
    }

    @DataProvider(name = "Datagenerated_IFCOND")
    public Object[][] arrfunc() {
        String[][] arr = new String[3][3];
        for (int x = 0; x < arr.length; x++) {

            for (int y = 0; y < arr[x].length; y++) {
                if (y == 0) {
                    arr[x][y] = VALID_Generate_name();
                }
                if (y == 1) {
                    arr[x][y] = ValidEmail_Generater();
                }
                if (y == 2) {
                    arr[x][y] = VALID_Generate_phone();
                } else {
                    arr[x][y] = inVALID_Generate_name();
                }
            }
        }
        return arr;
    }

    @DataProvider(name = "Testdata_generated2")
    public Object[][] Test_data2() {

        Object data[][] = new Object[2][3];
        data[0][0] = VALID_Generate_name();
        data[0][1] = VALID_Generate_name();
        data[0][2] = VALID_Generate_name();
        data[1][0] = VALID_Generate_name();
        data[1][1] = VALID_Generate_name();
        data[1][2] = VALID_Generate_name();
        return data;
    }

    public static ArrayList namelist() {
        ArrayList<String> namelist = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Faker f = new Faker();

        while (namelist.size() < 10) {
            String name2 = sc.next();
            String name = f.name().firstName();
            if (!namelist.contains(name)) {
                namelist.add(name);
            }
        }
        return namelist;
    }

    @DataProvider(name = "Datagenerated_IFCOND_t")
    public Object[][] arrfunc_t() {
        String[][] arr = new String[3][3];
        for (int x = 0; x < arr.length; x++) {

            for (int y = 0; y < arr[x].length; y++) {
                if (y == 0) {
                    arr[x][y] = (String) namelist().get(y);
                }
                if (y == 1) {
                    arr[x][y] = ValidEmail_Generater();
                }
                if (y == 2) {
                    arr[x][y] = VALID_Generate_phone();
                } else {
                    arr[x][y] = inVALID_Generate_name();
                }
            }
        }
        return arr;
    }

}

package DataMethod;

import com.github.javafaker.Faker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * docur
 * use java faker to generate array list for each filed
 * convert arraylist to string to store in excel file
 * create function to store data in excell file
 * create PSVM TO RUN FUNCTION AND STORE IN PRIJECT FILE
 */


public class generated_arraylist_JFK {
    static Faker f = new Faker();

    //Unique array list for Diffrents methods

    /**
     * @param Rows : is size of array list
     * @return
     */
    public static ArrayList namelist(int Rows) {
        ArrayList<String> namelist = new ArrayList<>();
        while (namelist.size() < Rows) {
            String name = f.name().firstName();
            if (!namelist.contains(name)) {
                namelist.add(name);
            }
        }
        return namelist;
    }

    public static ArrayList Emaillist(int Rows) {
        ArrayList<String> namelist2 = new ArrayList<>();
        while (namelist2.size() < Rows) {
            String name2 = f.internet().emailAddress();
            if (!namelist2.contains(name2)) {
                namelist2.add(name2);
            }
        }
        return namelist2;
    }

    public static ArrayList PhoneList(int Rows) {
        ArrayList<String> namelist2 = new ArrayList<>();
        while (namelist2.size() < Rows) {
            String name2 = f.numerify("##+##########");
            if (!namelist2.contains(name2)) {
                namelist2.add(name2);
            }
        }
        return namelist2;
    }

    /**
     * this function generate unique csv file with n number of raws and 4 coulums fileds
     *
     * @param n_rwos     : number of column length
     * @param list1name  : header name of column list1
     * @param list1      :array list 1
     * @param list2name  : header name of column list2
     * @param list2      :array list 2
     * @param list3name  : header name of column list3
     * @param list3      :array list 3
     * @param list4name: header name of column list4
     * @param list4      :array list 4
     * @return
     */


    public static String ArrayToString4Fields(int n_rwos, String list1name, ArrayList list1, String list2name, ArrayList list2, String list3name, ArrayList list3, String list4name, ArrayList list4) {
        String listData = list1name + list2name + list3name + list4name;
        for (int x = 0; x < n_rwos; x++) {
            listData = listData + "\n" + String.valueOf(list1.get(x) + "," + list2.get(x) + "," + list3.get(x) + "," + list4.get(x));
        }
        return listData;
    }

    /**
     * @param n_rwos    : number of column length
     * @param list1name : header name of column
     * @param list1     :array list
     * @return string data will saved and can be read as .CSV file extension
     */
    public static String ArrayToString1Fields(int n_rwos, String list1name, ArrayList list1) {
        String listData = list1name;
        for (int x = 0; x < n_rwos; x++) {
            listData = listData + "\n" + String.valueOf(list1.get(x));
        }
        return listData;
    }

    static void AddToNewEXCEL(String path, String content) throws IOException {
        try {
            File x = new File(path);
            File file = new File(path);

            if (!file.exists())
                file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (NullPointerException ignore) {

        }

    }

    public static int disition(int n) {
        return n;
    }

    public static void main(String[] args) throws IOException {
        if (disition(2) == 1) {

            AddToNewEXCEL(
                    System.getProperty("user.dir") + "\\Resources\\file1.csv", ArrayToString4Fields(6,
                            "FName,", namelist(6)
                            ,
                            "Lname,", namelist(6),
                            "Email,", Emaillist(6),
                            "Phone,", PhoneList(6)
                    )
            );
        } else {

            AddToNewEXCEL(
                    System.getProperty("user.dir") + "\\Resources\\file2.csv", ArrayToString1Fields(6,
                            "FName,", namelist(6)

                    )
            );

        }
    }

}

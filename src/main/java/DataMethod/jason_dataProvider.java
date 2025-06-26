package DataMethod;

import org.testng.annotations.DataProvider;

public class jason_dataProvider {
    /**
     * @return :3*1 array
     */
    @DataProvider(name = "MaterialRequest_type_input")
    public static Object[][] getDataFromDataprovider0() {
        return new Object[][]
                { {"طلب إدخال وغير قابل للإرجاع"}, {"طلب إدخال وقابل للإرجاع"}, {"طلب إخراج وغير قابل للإرجاع"} };
    }
    //this is 3*2 array....

    /**
     * @return :3*2 array
     */
    @DataProvider(name = "SearchProvider")
    public static Object[][] getDataFromDataprovider() {
        return new Object[][]
                {
                        {"sss", "bb"},
                        {"UUU", "gg"},
                        {"GG", "rrop"}
                };
    }

    //this is 3*3 array....

    /**
     * @return : 3*3 array
     */
    @DataProvider(name = "SearchProvider2")
    public static Object[][] getDataFromDataprovider2() {
        return new Object[][]
                {
                        {"sss", "bb", "p00o"},
                        {"UUU", "gg", "uuioj"},
                        {"GG", "rrop", "qqqq"}
                };
    }
    //this is 3*4 array....

    /**
     * @return :3*4 array
     */
    @DataProvider(name = "SearchProvider3")
    public static Object[][] getDataFromDataprovider3() {
        return new Object[][]
                {
                        {"sss", "bb", "p00o", "yrw"},
                        {"UUU", "gg", "uuioj", "yfdx"},
                        {"GG", "rrop", "qqqq", "p00ohgo"}
                };
    }

}

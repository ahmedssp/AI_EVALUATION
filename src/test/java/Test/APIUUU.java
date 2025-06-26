package Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;

import java.net.SocketTimeoutException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static DataMethod.ApachePOI.Read_ExcelSheet;
import static DataMethod.ApachePOI.insertExcel;
import static io.restassured.RestAssured.given;

public class APIUUU {
    static String baseURI = "http://35.176.249.135:9001/";
    static String ExcelPath = "/Resources/variable_test.xlsx";
    static String sheetNO = "Sheet1";
    public static void main(String[] args) throws InterruptedException {
        String Question = null;
        Response result_gen = null;
        String ChatOpt = "rag_online";
//        String ChatOpt = "rag_mongodb";
        int charPerSecond = 10;
        long v;
        int numberOfQuestion = Integer.parseInt((String) Read_ExcelSheet(ExcelPath, sheetNO)[0][8]);
        for (int c = 0; c < numberOfQuestion; c++) {
            Question = (String) Read_ExcelSheet(ExcelPath, sheetNO)[c][0];
            try {
                result_gen = GetAnswer_2(ChatOpt, Question,10000);
            } catch (Exception e) {
                insertExcel(e.getMessage() , c + 1, 6, ExcelPath); //logic   [][constant =1]
                System.out.println("_____________________________>" + (c + 1));
                continue;            }
            v = (result_gen.getBody().asString().replace(" ", "").chars().count()) * 1000 / (result_gen.time());
            if (result_gen.statusCode() != 200) {
                insertExcel("request Fail , status code: "+result_gen.statusCode(), c + 1, 6, ExcelPath); //logic   [][constant =1]
                System.out.println("_____________________________>" + (c + 1));
                continue;
            }

//            if (v > charPerSecond) {
//                insertExcel("response time Fail as char per second is larger than:" + charPerSecond, c + 1, 6, ExcelPath); //logic   [][constant =1]
//                System.out.println("_____________________________>" + (c + 1));
//                continue;
//            }
            insertExcel(result_gen.getBody().asString(), c + 1, 1, ExcelPath); //logic   [][constant =1]
            insertExcel(dateandtime(), c + 1, 2, ExcelPath); //logic   [][constant =1]
            insertExcel(ChatOpt, c + 1, 3, ExcelPath); //logic   [][constant =1]
            insertExcel(String.valueOf("response time: " + result_gen.getTime() + "char pert second: " + v), c + 1, 4, ExcelPath); //logic   [][constant =1]
            System.out.println("_____________________________>" + (c + 1));
        }
    }
    public static String dateandtime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    public static Response GetAnswer_2(String ChatOpt, String question ,long socketTimeoutInMillis) {
        JSONObject bodyData = new JSONObject();
        bodyData.put("user_id", "1");
        bodyData.put("query", question);
        // Send POST request
        Response response = given()
                .contentType(ContentType.JSON)
                .body(bodyData.toString())
                .config(io.restassured.RestAssured.config()
                        .httpClient(io.restassured.config.HttpClientConfig.httpClientConfig()
                                .setParam("http.socket.timeout", socketTimeoutInMillis))) // Set socket timeout

                .when()
                .post(baseURI + ChatOpt);
        // Assert the response status code
        Assert.assertEquals(response.statusCode(), 200);
        // Print the response body
        return response;
    }

}

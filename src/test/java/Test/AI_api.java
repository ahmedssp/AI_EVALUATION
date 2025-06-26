package Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static DataMethod.ApachePOI.Read_ExcelSheet;
import static DataMethod.ApachePOI.insertExcel;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsNull.notNullValue;

public class AI_api {
    static String baseURI = "http://35.176.249.135:9001/";
    static String ExcelPath = "/Resources/variable_test.xlsx";
    static String sheetNO = "Sheet1";

    @Test
    public void rag_online() {
        // Create JSON request body
        JSONObject bodyData = new JSONObject();
        bodyData.put("user_id", "1");
        bodyData.put("query", "what is xyz?");
        // Send POST request
        Response response = given()
                .contentType(ContentType.JSON)
                .body(bodyData.toString())
                .when()
                .post(baseURI + "rag_online");
        // Assert the response status code
        Assert.assertEquals(response.statusCode(), 200);
        // Print the response body
//        String responseBody = response.prettyPrint();
        System.out.println(response.getBody().asString());
    }

    public static Response GetAnswer(String ChatOpt, String question) {
        JSONObject bodyData = new JSONObject();
        bodyData.put("user_id", "1");
        bodyData.put("query", question);
        // Send POST request
        Response response = given()
                .contentType(ContentType.JSON)
                .body(bodyData.toString())
                .when()
                .post(baseURI + ChatOpt);
        // Assert the response status code
        Assert.assertEquals(response.statusCode(), 200);
        // Print the response body
        return response;
    }

    public static String dateandtime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static void main(String[] args) throws InterruptedException {
        String Question = null;
        Response result_gen = null;
        String ChatOpt = "rag_online";
//        String ChatOpt = "rag_mongodb";
        int charPerSecond = 550;
        long v;
        long numberOfQuestion = Long.parseLong((String) Read_ExcelSheet(ExcelPath, sheetNO)[0][8]);
        for (int c = 0; c < numberOfQuestion; c++) {
            Question = (String) Read_ExcelSheet(ExcelPath, sheetNO)[c][0];
            result_gen = GetAnswer(ChatOpt, Question);
            v = (result_gen.getBody().asString().replace(" ", "").chars().count()) * 1000 / (result_gen.time());
            if (result_gen.statusCode() != 200) {
                insertExcel("request Fail , status code: " + result_gen.statusCode(), c + 1, 6, ExcelPath); //logic   [][constant =1]
                System.out.println("_____________________________>" + (c + 1));
                continue;
            }
            if (v > charPerSecond) {
                insertExcel("response time Fail as char per second is larger than:" + charPerSecond, c + 1, 6, ExcelPath); //logic   [][constant =1]
                System.out.println("_____________________________>" + (c + 1));
                continue;
            }
            insertExcel(result_gen.getBody().asString(), c + 1, 1, ExcelPath); //logic   [][constant =1]
            insertExcel(dateandtime(), c + 1, 2, ExcelPath); //logic   [][constant =1]
            insertExcel(ChatOpt, c + 1, 3, ExcelPath); //logic   [][constant =1]
            insertExcel(String.valueOf("response time: " + result_gen.getTime() + "char pert second: " + v), c + 1, 4, ExcelPath); //logic   [][constant =1]
            System.out.println("_____________________________>" + (c + 1));
        }
    }
}

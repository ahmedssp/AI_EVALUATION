package Test;


import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.ConnectionClosedException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.SocketTimeoutException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static DataMethod.ApachePOI.Read_ExcelSheet;
import static DataMethod.ApachePOI.insertExcel;
import static io.restassured.RestAssured.given;

public class WWWW {
    static String baseURI = "http://35.176.249.135:9001/";
    static String ExcelPath = "/Resources/Genaral_performnce_questions.xlsx";
    static String sheetNO = "Sheet1";
    static long timeout = 60000;

    public static Response GetAnswer(String ChatOpt, String question) throws SocketTimeoutException ,ConnectionClosedException {

        JSONObject bodyData = new JSONObject();
        bodyData.put("user_id", "1");
        bodyData.put("query", question);
        // Send POST request
        Response response = given()
                .contentType(ContentType.JSON)
                .body(bodyData.toString())
                .config(RestAssured.config()
                        .httpClient(HttpClientConfig.httpClientConfig()
//                                .setParam("http.connection.timeout", 10000) // Connection timeout in milliseconds
                                .setParam("http.socket.timeout", 60000))) // Set socket timeout

                .when()
                .post(baseURI + ChatOpt);
        // Assert the response status code
        Assert.assertEquals(response.statusCode(), 200);

        return response;
    }

    public static String CurrentDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void test() {
        String Question = null;
        Response result_gen = null;

        Map<Integer, String> ChatOpt_ = new HashMap<>();
        ChatOpt_.put(1, "rag_online");
        ChatOpt_.put(2, "rag_mongodb");
        long numberOfQuestion = Long.parseLong((String) Read_ExcelSheet(ExcelPath, sheetNO)[0][8]);


        for (int c = 0; c < numberOfQuestion; c++) {
            Question = (String) Read_ExcelSheet(ExcelPath, sheetNO)[c][0];
            try {
                result_gen = GetAnswer(ChatOpt_.get(1), Question);
                insertExcel(result_gen.asPrettyString(), c + 1, 1, ExcelPath); //logic   [][constant =1]
                insertExcel(CurrentDateTime(), c + 1, 2, ExcelPath); //logic   [][constant =1]
                insertExcel(ChatOpt_.get(1), c + 1, 3, ExcelPath); //logic   [][constant =1]
            }
            catch (SocketTimeoutException e) {
                insertExcel("response time Fail it is larger than:" + timeout + " " + e.getMessage(), c + 1, 6, ExcelPath); //logic   [][constant =1]
                System.out.println(">-------------->>" + (c + 1));
                continue;
            } catch (ConnectionClosedException e2){
                System.out.println("##########\n"+e2+"##########");
                continue;
            }

            System.out.println(">-------------->>" + (c + 1));
        }
    }
}





package Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static DataMethod.ApachePOI.ReadExcelSheetmulty;
import static DataMethod.ApachePOI.Read_ExcelSheet;

public class RestClient {
    static String sheetNO = "Sheet1";
    static String ExcelPath = "/Resources/Genaral_performnce_questions.xlsx";


    public static String getChatResponse(String query, String opt) {
        // Base URL for the API
        String baseURL = "http://35.176.249.135:9001/";

        // Request payload
        String payload = "{\"user_id\": \"1\", \"query\": \"" + query + "\"}";

        // Specify request
        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(payload);

        // Send POST request and get response
        Response response = request.post(baseURL + opt);

        // Extract response body as a string

        return response.getBody().asString();
    }

//    public static Response getChatResponse2(String query, String chat) {
//        // Base URL for the API
//        String baseURL = "http://35.176.249.135:9001/";
//
//        JSONObject bodyData = new JSONObject();
//        bodyData.put("user_id", "1");
//        bodyData.put("query", query);
//
//        // Specify request
//        RequestSpecification request = RestAssured.given()
//                .header("Content-Type", "application/json")
//                .body(bodyData);
//
//        // Send POST request and get response
//        Response response = request.post(baseURL + chat);
//
//        // Extract response body as a string
////        String responseBody = response.getBody().asString();
//
//        return response;
//    }

    public static void main(String[] args) throws InterruptedException {
//        System.out.println(getChatResponse("what is ai", "chat"));
        int count =0;
        // Send the request 100 times with one-second interval
        for (int i = 0; i < 2; i++) {
           String  Question = (String) Read_ExcelSheet("/resources/variable_test.xlsx", "Sheet1")[i][0];;
            System.out.println(i+"______________________"+Question+"_____________________");
            String responseBody = getChatResponse(Question, "chat");
            if(responseBody.length()<10){count++;System.out.println("fail_:"+ count);}
            System.out.println(responseBody);
            System.out.println("*********************************************************");

        }
        System.out.println("************************** faild:"+count+" ************************************");
//        Thread.sleep(1000); // Sleep for one second

    }
}


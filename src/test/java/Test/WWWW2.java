package Test;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.ConnectionClosedException;
import org.json.JSONObject;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static DataMethod.ApachePOI.*;
import static io.restassured.RestAssured.given;
public class WWWW2 {
    static String baseURI = "https://ai-platform.wakeb.tech/api/api";
//    static String ExcelPath = "/Resources/Genaral_performnce_questions.xlsx";
//    static String ExcelPath = "/Resources/militery_knowledg_questions.xlsx";
    static String ExcelPath = "/Resources/military_weapons_questions.xlsx";


    static String sheetNO = "Sheet1";
    static long timeout = 60000;
    String Question = null;
    Response result_gen = null;
    int start_question = (99) - 1;
    Map<Integer, String> ChatOpt_ = new HashMap<>();


    public static Response GetAnswer(String ChatOpt, String question) throws SocketTimeoutException, ConnectionClosedException, SocketException {

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
    public String getChatResponse() {
        // Base URL for the API
        String baseURL = "http://35.176.249.135:9001";

        // Request payload
        String payload = "{\"user_id\": \"1\", \"query\": \"what are most common chess openings for white?\"}";

        // Specify request
        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(payload);

        // Send POST request and get response
        Response response = request.post(baseURL + "/chat");

        // Extract response body as a string
        String responseBody = response.getBody().asString();

        return responseBody;
    }


    public static String CurrentDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void IncertAll(int c, int z, Response result_gen, Map<Integer, String> ChatOpt_, String ExcelPath, int VersionCoulum) {
        InsertExcelMulty(result_gen.asPrettyString(), (c + 1), VersionCoulum, ExcelPath, ChatOpt_.get(z)); //logic   [][constant =1]
        InsertExcelMulty(CurrentDateTime(), (c + 1), 2, ExcelPath, ChatOpt_.get(z)); //logic   [][constant =1]
        InsertExcelMulty(String.valueOf(result_gen.time() / 1000), (c + 1), 4, ExcelPath, ChatOpt_.get(z)); //logic   [][constant =1]
        InsertExcelMulty(ChatOpt_.get(z), (c + 1), 3, ExcelPath, ChatOpt_.get(z)); //logic   [][constant =1]
    }

    @Test
    public void test() throws SocketTimeoutException, ConnectionClosedException {
        String Question = null;
        Response result_gen = null;
        int start_question = (99) - 1;

        Map<Integer, String> ChatOpt_ = new HashMap<>();
        ChatOpt_.put(1, "rag_online");
        ChatOpt_.put(2, "rag_mongodb");
        Map<Integer, String> sheet_number = new HashMap<>();
        sheet_number.put(1, "Sheet1");
        sheet_number.put(2, "Sheet2");

        for (int z = 1; z <= 2; z++) {
            long numberOfQuestion = Long.parseLong((String) ReadExcelSheetmulty(ExcelPath, sheet_number.get(z))[0][8]);

            for (int c = start_question; c < numberOfQuestion; c++) {

                Question = (String) Read_ExcelSheet(ExcelPath, sheet_number.get(z))[c][0];
                try {
                    result_gen = GetAnswer(ChatOpt_.get(z), Question);
                } catch (SocketTimeoutException e) {
                    insertExcel("response time Fail it is larger than:" + timeout + " " + e.getMessage(), c + 1, 6, ExcelPath); //logic   [][constant =1]
                    System.out.println(">-------------->>" + (c + 1));
                    continue;
                } catch (ConnectionClosedException e2) {
                    System.out.println("##########\n" + e2 + "\n##########\n");
//                result_gen = GetAnswer(ChatOpt_.get(2), Question);
                    continue;
                } catch (Exception E) {
                    System.out.println("***********\n" + E + "\n*************");
                }
                insertExcel(result_gen.asPrettyString(), (int) (c + 1), 1, ExcelPath); //logic   [][constant =1]
                insertExcel(CurrentDateTime(), (int) (c + 1), 2, ExcelPath); //logic   [][constant =1]
                insertExcel(String.valueOf(RestAssured.get("[^1^][1]").time()) + "//" + String.valueOf(result_gen.time()), (int) (c + 1), 4, ExcelPath); //logic   [][constant =1]
                insertExcel(ChatOpt_.get(2), (int) (c + 1), 3, ExcelPath); //logic   [][constant =1]
                System.out.println(">-------------->>" + (c + 1));

            }
        }
    }
    @Test
    public void testedj()  {
//        WebDriverManager.firefoxdriver().setup();
//
//        d = new FirefoxDriver();
////        d.navigate().to("https://chat.openai.com/auth/login");
//        d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    public void test2() {
        String Question = null;
        Response result_gen = null;
        int start_question = (1) - 1;
        int VersionCoulum = 9;
        Map<Integer, String> ChatOpt_ = new HashMap<>();
        ChatOpt_.put(1, "rag_online");
        ChatOpt_.put(2, "rag_mongodb");

        for (int z = 1; z <= 2; z++) {

            long numberOfQuestion = Long.parseLong((String) ReadExcelSheetmulty(ExcelPath, ChatOpt_.get(z))[0][8]);

            for (int c = start_question; c < numberOfQuestion; c++) {

                Question = (String) ReadExcelSheetmulty(ExcelPath, ChatOpt_.get(z))[c][0];
                System.out.println(Question);

                try {

                    result_gen = GetAnswer(ChatOpt_.get(z), Question);
                    IncertAll(c, z, result_gen, ChatOpt_, ExcelPath, VersionCoulum);

                } catch (SocketTimeoutException e) {

                    InsertExcelMulty("response time Fail it is larger than:" + timeout + (e.getMessage()) + " millisecond ", c + 1, 6, ExcelPath, ChatOpt_.get(z)); //logic   [][constant =1]
                    System.out.println(">-------------->>" + (c + 1));
                    continue;

                } catch (ConnectionClosedException | SocketException e2) {
                    try {
                        result_gen = GetAnswer(ChatOpt_.get(z), Question);
                        IncertAll(c, z, result_gen, ChatOpt_, ExcelPath, VersionCoulum);
                     //SocketTimeoutException | ConnectionClosedException | SocketException  e
                    } catch (Exception e) {
                        InsertExcelMulty("response time :" + timeout + " " + e.getMessage(), c + 1, 6, ExcelPath, ChatOpt_.get(z)); //logic   [][constant =1]
                        System.out.println("##########\n" + e.getMessage() + "\n##########\n" + (c + 1));
                        continue;
                    }

                } catch (Exception E) {

                    InsertExcelMulty("fail request " + E.getMessage(), c + 1, 6, ExcelPath, ChatOpt_.get(z)); //logic   [][constant =1]
                    System.out.println("***********\n" + E.getMessage() + "\n*************" + (c + 1));
                    continue;
                }

                System.out.println(">-------------->>" + (c + 1)+"_"+ChatOpt_.get(z));
            }
        }

    }
    @Test
    public void test3() {
        String Question = null;
        Response result_gen = null;
        int start_question = (1) - 1;
        int VersionCoulum = 9;

        Map<Integer, String> ChatOpt_ = new HashMap<>();
        ChatOpt_.put(1, "rag_online");
        ChatOpt_.put(2, "rag_mongodb");

        Map<Integer, String> files = new HashMap<>();
        files.put(1, "/Resources/Genaral_performnce_questions.xlsx");
        files.put(2, "/Resources/militery_knowledg_questions.xlsx");
        files.put(3, "/Resources/military_weapons_questions.xlsx");

        for (int f = 1; f <= files.size(); f++) {

            for (int z = 1; z <= 2; z++) {

                long numberOfQuestion = Long.parseLong((String) ReadExcelSheetmulty(files.get(f), ChatOpt_.get(z))[0][8]);

                for (int c = start_question; c < numberOfQuestion; c++) {

                    Question = (String) ReadExcelSheetmulty(files.get(f), ChatOpt_.get(z))[c][0];
                    System.out.println(Question);

                    try {

                        result_gen = GetAnswer(ChatOpt_.get(z), Question);
                        IncertAll(c, z, result_gen, ChatOpt_, files.get(f), VersionCoulum);

                    } catch (SocketTimeoutException e) {

                        InsertExcelMulty("response time Fail it is larger than:" + timeout + (e.getMessage()) + " millisecond ", c + 1, 6, files.get(f), ChatOpt_.get(z)); //logic   [][constant =1]
                        System.out.println(">-------------->>" + (c + 1));
                        continue;

                    } catch (ConnectionClosedException | SocketException e2) {
                        try {
                            result_gen = GetAnswer(ChatOpt_.get(z), Question);
                            IncertAll(c, z, result_gen, ChatOpt_, files.get(f), VersionCoulum);
                            //SocketTimeoutException | ConnectionClosedException | SocketException  e
                        } catch (Exception e) {
                            InsertExcelMulty("response time :" + timeout + " " + e.getMessage(), c + 1, 6, files.get(f), ChatOpt_.get(z)); //logic   [][constant =1]
                            System.out.println("##########\n" + e.getMessage() + "\n##########\n" + (c + 1));
                            continue;
                        }

                    } catch (Exception E) {

                        InsertExcelMulty("fail request " + E.getMessage(), c + 1, 6, files.get(f), ChatOpt_.get(z)); //logic   [][constant =1]
                        System.out.println("***********\n" + E.getMessage() + "\n*************" + (c + 1));
                        continue;
                    }

                    System.out.println(">-------------->>" + (c + 1) + "_" + ChatOpt_.get(z)+ "_"+files.get(f));
                }
            }
        }

    }
    @Test
    public void test4() {
        String Question = null;
        Response result_gen = null;
        int start_question = (130) - 1;
        int VersionCoulum = 9;

        Map<Integer, String> ChatOpt_ = new HashMap<>();
        ChatOpt_.put(1, "rag_online"); //RAG ONLINE
        ChatOpt_.put(2, "rag_mongodb"); // BASE
        ChatOpt_.put(3, "chat"); //RAG OFLINE

        Map<Integer, String> files = new HashMap<>();
        files.put(1, "/Resources/Genaral_performnce_questions.xlsx");
        files.put(2, "/Resources/militery_knowledg_questions.xlsx");
        files.put(3, "/Resources/military_weapons_questions.xlsx");

        for (int f = 1; f <= files.size(); f++) {

            for (int z = 1; z <= ChatOpt_.size(); z++) {

                long numberOfQuestion = Long.parseLong((String) ReadExcelSheetmulty(files.get(f), ChatOpt_.get(z))[0][8]);

                for (int c = start_question; c < numberOfQuestion; c++) {

                    Question = (String) ReadExcelSheetmulty(files.get(f), ChatOpt_.get(z))[c][0];
                    System.out.println(Question);

                    try {
                        result_gen = GetAnswer(ChatOpt_.get(z), Question);
                        IncertAll(c, z, result_gen, ChatOpt_, files.get(f), VersionCoulum);

                    } catch (SocketTimeoutException e) {

                        InsertExcelMulty("response time Fail it is larger than:" + timeout + (e.getMessage()) + " millisecond ", c + 1, 6, files.get(f), ChatOpt_.get(z)); //logic   [][constant =1]
                        System.out.println(">-------------->>" + (c + 1));
                        continue;

                    } catch (ConnectionClosedException | SocketException e2) {
                        try {
                            result_gen = GetAnswer(ChatOpt_.get(z), Question);
                            IncertAll(c, z, result_gen, ChatOpt_, files.get(f), VersionCoulum);
                            //SocketTimeoutException | ConnectionClosedException | SocketException  e
                        } catch (Exception e) {
                            InsertExcelMulty("response time :" + timeout + " " + e.getMessage(), c + 1, 6, files.get(f), ChatOpt_.get(z)); //logic   [][constant =1]
                            System.out.println("##########\n" + e.getMessage() + "\n##########\n" + (c + 1));
                            continue;
                        }

                    } catch (Exception E) {

                        InsertExcelMulty("fail request " + E.getMessage(), c + 1, 6, files.get(f), ChatOpt_.get(z)); //logic   [][constant =1]
                        System.out.println("***********\n" + E.getMessage() + "\n*************" + (c + 1));
                        continue;
                    }

                    System.out.println(">-------------->>" + (c + 1) + "_" + ChatOpt_.get(z)+ "_"+files.get(f));
                }
            }
        }

    }
    public static Response GetAnswer_new(String ChatOpt, String question) throws SocketTimeoutException, ConnectionClosedException, SocketException {

        JSONObject bodyData = new JSONObject();
        bodyData.put("user_id", "1");
        bodyData.put("query", question);
        bodyData.put("session_id", new Faker().internet().domainName()+ new Random(1000));
        String url = "https://aip.wakeb.tech/chat";

        RestAssuredConfig config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", 100000)
                        .setParam("http.socket.timeout", 100000));

        Response response = RestAssured.given()
                .config(config)
                .header("Content-Type", "application/json")
                .body(bodyData)
                .post(url);

        // Assert the response status code
        Assert.assertEquals(response.statusCode(), 200);

        return response;
    }
    @Test
    public void testChatApi() throws SocketException, SocketTimeoutException, ConnectionClosedException, InterruptedException {

       for(int x=0; x<10;x++){
           // Send the POST request and get the response
           Response response = GetAnswer_new("/chat","what is ai ");
           // Print the response (for debugging purposes)
           System.out.println("Response Status Code: " + response.getStatusCode());
           System.out.println("Response Body: " + response.getBody().asString());
//           Thread.sleep(30000);
           System.out.println("__________________________________________________________:"+x);
       }

    }
@Test
    public void tttttttt6r6() throws InterruptedException {
    for(int x=0; x<100;x++){
        String url = "https://aip.wakeb.tech/chat";
        String ccc=new Faker().internet().domainName()+ new Random(1000);

        String jsonBody = "{\"user_id\": \"1\", \"query\": \"what are ai\", \"session_id\": \"297tg599_ghjhghhgfdfdv_522808\"}";

        RestAssuredConfig config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", 100000)
                        .setParam("http.socket.timeout", 100000));

        Response response = RestAssured.given()
                .config(config)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .post(url);
//        Thread.sleep(40000);
        System.out.println("Response: " + response.getBody().asString());
        System.out.println(ccc);
        System.out.println("__________________________________________________________:"+x);
    }
  }
}






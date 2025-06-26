package Handler;

import org.json.JSONObject;
import org.testng.Assert;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import java.net.SocketTimeoutException;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.baseURI;
public class APIMethod {


    public static Response GetAnswer(String ChatOpt, String question, long timeoutInMillis) {
        JSONObject bodyData = new JSONObject();
        bodyData.put("user_id", "1");
        bodyData.put("query", question);

        Response response = null;

        // Send POST request
        response = given()
                .contentType(ContentType.JSON)
                .body(bodyData.toString())
                .when()
                .post(baseURI + ChatOpt)
                .then()
                .extract()
                .response();

        // Assert the response status code
        Assert.assertEquals(response.statusCode(), 200, "Response status code is not 200");

        return response;
    }
}

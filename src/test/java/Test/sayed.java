package Test;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class sayed {
    @Test
    public void test4ooo() {

        // Base URL of the BentoML service
        String baseUrl = "http://35.176.249.135:9001";

        // Request body
        String requestBody = "{ \"prompt\": \"What are Large Language Models?\", \"tokens\": null }";

        // Make the request using Rest Assured
        String response = given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .accept("text/event-stream")
                .body(requestBody)
                .when()
                .post("/generate")
                .then()
                .statusCode(200) // Or any expected status code
                .extract()
                .response()
                .asString();

        // Print the response
        System.out.println("Response: " + response);

    }

}

package Utility;//package Utility;
//
//import org.testng.Assert;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import org.testng.annotations.Test;
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.*;
//
//
//public class ApiMethod {
//    public static void valid_login(){
//        Response res = given().contentType(ContentType.JSON).body(Bodydata.toString()).when().post(BASE_URL + "api/auth/login?email");
//        res.prettyPrint();
//        token = res.body().jsonPath().get("access_token");
//        Assert.assertEquals(res.statusCode(), 200);
//        res.then().statusCode(200).body("access_token", notNullValue());
//    }
//}

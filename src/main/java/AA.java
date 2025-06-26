import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

public class AA {
    public static void main(String[] args) {

        Response Response = RestAssured.given().request(Method.GET, "/all");
        JsonPath jsonPathObj = Response.jsonPath();
        // Get all employee IDs between 15 and 300
        List<Map> employees = jsonPathObj.get("company.employee.findAll { employee -> employee.id >= 15 && employee.id <= 300 }");
    }



}

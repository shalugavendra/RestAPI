package api.methods;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static api.utilities.Utils.getGlobalValue;


public class ApiMethods {

    public static Response getuser() throws IOException {

        Response response = given().baseUri(getGlobalValue("base_url")).contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().get("api/users?page=2").then().extract().response();
        return response;
    }

}

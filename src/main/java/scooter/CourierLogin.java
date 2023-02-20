package scooter;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierLogin {
    static Courier courier;

    @Step("scooter.Courier Login")
    public static Response courierLogin(Courier courier) {
        Response responseLogin =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier/login");
        return responseLogin;
    }
}

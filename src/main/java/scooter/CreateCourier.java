package scooter;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreateCourier {
    static Courier courier;

    @Step("Create courier")
    public static Response createCourier(Courier courier) {
        Response responseCreate =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courier)
                        .when()
                        .post("/api/v1/courier");
        return responseCreate;
    }
}

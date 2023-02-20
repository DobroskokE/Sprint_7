package scooter;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CancelOrder {
    @Step("Cansel order")
    public static Response cancelOrder(Track track) {
        Response responseCreateOrder =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(track)
                        .when()
                        .put("/api/v1/orders/cancel");
        return responseCreateOrder;
    }
}

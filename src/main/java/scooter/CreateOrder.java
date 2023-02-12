package scooter;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreateOrder {
    Order order;

    @Step("Create order")
    public static Response createOrder(Order order) {
        Response responseCreateOrder =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(order)
                        .when()
                        .post("/api/v1/orders");
        return responseCreateOrder;
    }
}

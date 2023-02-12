package scooterTests;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class OrdersTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Check get orders list")
    @Step("Get orders list")
    public void ordersIsNotNull() {
        given()
                .get("/api/v1/orders")
                .then().assertThat().body("orders", notNullValue())
                .and()
                .statusCode(200);
    }
}

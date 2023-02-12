package scooterTests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import scooter.*;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)

public class CreateOrderTest {
    private List<String> color;

    public CreateOrderTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0}") // добавили аннотацию
    public static Object[][] getSexData() {
        return new Object[][]{
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("BLACK", "GREY")},
                {List.of()},
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Check create order with different color")
    public void createOrderWithDifferentColorAndTrackIsNotNullTest() {

        Order order = new Order(
                TestData.randomFirstName,
                TestData.randomLastName,
                TestData.randomAddress,
                TestData.randomMetroStation,
                TestData.randomPhone,
                TestData.randomRentTime,
                TestData.randomDeliveryDate,
                TestData.randomComment,
                color
        );

        Response responseCreateOrder = CreateOrder.createOrder(order);
        Track track = responseCreateOrder.body().as(Track.class);

        responseCreateOrder
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("track", notNullValue());

        CancelOrder.cancelOrder(track);
    }
}

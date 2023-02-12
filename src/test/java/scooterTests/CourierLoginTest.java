package scooterTests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import scooter.Courier;
import scooter.CourierLogin;
import scooter.CreateCourier;
import scooter.DeleteCourier;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CourierLoginTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    String randomLogin = RandomStringUtils.randomAlphabetic(10);
    String randomPassword = RandomStringUtils.randomAlphabetic(10);

    @Test
    @DisplayName("Check auth with required fields")
    public void courierAuthWithRequiredFieldsReturn200AndIdTest() {
        Courier courier = new Courier(randomLogin, randomPassword);

        CreateCourier.createCourier(courier);

        CourierLogin.courierLogin(courier).then().assertThat().statusCode(200)
                .and()
                .body("id", notNullValue());

        DeleteCourier.deleteCourier(courier);
    }

    @Test
    @DisplayName("Check status and error message with wrong password")
    public void courierAuthWithWrongPasswordReturn404AndErrorMessageTest() {
        Courier courier = new Courier(randomLogin, randomPassword);

        CreateCourier.createCourier(courier);

        Courier wrongCourier = new Courier(randomLogin, "fjf");
        CourierLogin.courierLogin(wrongCourier).then().assertThat().statusCode(404)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));

        DeleteCourier.deleteCourier(courier);
    }

    @Test
    @DisplayName("Check status and error message with non-existent login")
    public void courierAuthLoginDoesNotExistReturn404AndErrorMessageTest() {
        Courier courier = new Courier(randomLogin, randomPassword);

        CreateCourier.createCourier(courier);

        Courier wrongCourier = new Courier("rfdweefz", randomPassword);
        CourierLogin.courierLogin(wrongCourier).then().assertThat().statusCode(404)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));

        DeleteCourier.deleteCourier(courier);
    }

    @Test
    @DisplayName("Check status and error message without login")
    public void courierAuthWithoutLoginReturn404AndErrorMessageTest() {
        Courier courier = new Courier(randomLogin, randomPassword);

        CreateCourier.createCourier(courier);

        Courier wrongCourier = new Courier("", randomPassword);
        CourierLogin.courierLogin(wrongCourier).then().assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));

        DeleteCourier.deleteCourier(courier);
    }

    @Test
    @DisplayName("Check status and error message without password")
    public void courierAuthWithoutPasswordReturn404AndErrorMessageTest() {
        Courier courier = new Courier(randomLogin, randomPassword);

        CreateCourier.createCourier(courier);

        Courier wrongCourier = new Courier(randomLogin, "");
        CourierLogin.courierLogin(wrongCourier).then().assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));

        DeleteCourier.deleteCourier(courier);
    }
}
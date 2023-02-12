package scooterTests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import scooter.Courier;
import scooter.CreateCourier;
import scooter.DeleteCourier;

import static org.hamcrest.Matchers.equalTo;

public class CreateCourierTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    String randomLogin = RandomStringUtils.randomAlphabetic(10);
    String randomPassword = RandomStringUtils.randomAlphabetic(10);
    String randomFirstName = RandomStringUtils.randomAlphabetic(6);

    @Test
    @DisplayName("Check create courier with all fields")
    public void createCourierWithAllFieldsReturn201AndTrueTest() {
        Courier courier = new Courier(randomLogin, randomPassword, randomFirstName);

        CreateCourier.createCourier(courier).then().assertThat().statusCode(201)
                .and()
                .body("ok", equalTo(true));

        DeleteCourier.deleteCourier(courier);

    }

    @Test
    @DisplayName("Check create courier with required fields")
    public void createCourierWithRequiredFieldsReturn201AndTrueTest() {
        Courier courier = new Courier(randomLogin, randomPassword);

        CreateCourier.createCourier(courier).then().assertThat().statusCode(201)
                .and()
                .body("ok", equalTo(true));

        DeleteCourier.deleteCourier(courier);
    }

    @Test
    @DisplayName("Check status and error message when create the same courier")
    public void createTheSameCourierReturn409AndErrorMessageTest() {
        Courier courier = new Courier(randomLogin, randomPassword);

        CreateCourier.createCourier(courier);

        CreateCourier.createCourier(courier).then().assertThat().statusCode(409)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));

        DeleteCourier.deleteCourier(courier);
    }

    @Test
    @DisplayName("Check status and error message when create courier without login")
    public void createCourierWithoutLoginReturn400AndErrorMessageTest() {
        Courier courier = new Courier("", randomPassword);

        CreateCourier.createCourier(courier).then().assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Check status and error message when create courier without password")
    public void createCourierWithoutPasswordReturn400AndErrorMessageTest() {
        Courier courier = new Courier(randomLogin, "");

        CreateCourier.createCourier(courier).then().assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}

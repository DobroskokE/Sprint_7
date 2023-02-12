package scooter;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
public class DeleteCourier {
    Courier courier;

    @Step("Delete courier")
    public static void deleteCourier(Courier courier) {
        Response responseDelete =
                given()
                        .delete("/api/v1/courier/" + CourierLogin.courierLogin(courier).jsonPath().getString("id"));
    }
}


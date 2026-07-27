package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.OrderModel;
import static data.OrderData.*;

import static io.restassured.RestAssured.given;

public class OrderSteps {

    @Step("Создание заказа без авторизации")
    public static Response createOrder(OrderModel order) {
        return given()
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post(CREATE_ORDER_PATH)
                .then()
                .extract().response();
    }

    @Step("Создание заказа с авторизацией")
    public static Response createOrderWithAuth(OrderModel order, String token) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(order)
                .when()
                .post(CREATE_ORDER_PATH)
                .then()
                .extract().response();
    }

    @Step("Получение всех ингредиентов")
    public static Response getIngredients() {
        return given()
                .get(GET_INGREDIENTS_PATH)
                .then()
                .extract().response();
    }
}

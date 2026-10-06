package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.UserModel;

import static data.UserData.*;
import static io.restassured.RestAssured.given;

public class UserSteps {

    @Step("Создание пользователя")
    public static Response createUser(UserModel user) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(CREATE_USER_PATH);
        String token = response.then().extract().path("accessToken");
        if(token != null) {
            user.setToken(token);
        }
        return response;
    }

    @Step("Регистрация пользователя")
    public static Response loginUser(UserModel user) {
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(LOGIN_USER_PATH)
                .then()
                .extract().response();
    }

    @Step("Удаление пользователя")
    public static Response deleteUser(UserModel user) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", user.getToken())
                .when()
                .delete(DELETE_USER_PATH)
                .then()
                .extract().response();
    }
}

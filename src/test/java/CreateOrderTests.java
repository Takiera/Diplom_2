import io.qameta.allure.Description;
import model.OrderModel;
import org.junit.Test;
import steps.OrderSteps;

import java.util.List;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static steps.OrderSteps.createOrder;
import static steps.OrderSteps.createOrderWithAuth;
import static steps.UserSteps.createUser;

public class CreateOrderTests extends BaseUserTest{

    private List<String> getAllIngredients() {
        return OrderSteps.getIngredients().jsonPath().getList("data._id", String.class);
    }

    @Test
    @Description("Проверка успешного создания заказа с авторизацией")
    public void createOrderWithAuthSuccess() {
        createUser(user);
        OrderModel order = new OrderModel(getAllIngredients().subList(0,2));
        createOrderWithAuth(order, user.getToken())
                .then()
                .statusCode(HTTP_OK)
                .body("success", equalTo(true));
    }

    @Test
    @Description("Проверка успешного создания без авторизации")
    public void createOrderWithoutAuth() {
        OrderModel order = new OrderModel(getAllIngredients().subList(0,2));
        createOrder(order)
                .then()
                .statusCode(HTTP_OK)
                .body("success", equalTo(true));
    }

    @Test
    @Description("Проверка, что попытка создать заказ без ингредиентов возвращает исключение")
    public void createOrderWithoutIngredientsReturnException() {
        createUser(user);
        OrderModel order = new OrderModel(List.of());
        createOrderWithAuth(order, user.getToken())
                .then()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @Description("Проверка, что попытка создать заказ с неправильным хешем ингредиентов возвращает исключение")
    public void createOrderWithWrongIngredientsReturnException() {
        createUser(user);
        OrderModel order = new OrderModel(List.of("a", "b", "c"));
        createOrderWithAuth(order, user.getToken())
                .then()
                .statusCode(HTTP_INTERNAL_ERROR);
    }

}

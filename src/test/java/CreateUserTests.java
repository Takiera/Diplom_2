import model.UserModel;
import org.junit.Test;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static steps.UserSteps.createUser;

public class CreateUserTests extends BaseUserTest{

    @Test
    public void createUserSuccess() {
        createUser(user)
                .then()
                .statusCode(HTTP_OK)
                .body("success", equalTo(true));
    }

    @Test
    public void createDuplicateUserReturnException() {
        createUser(user);
        createUser(user)
                .then()
                .statusCode(HTTP_FORBIDDEN)
                .body("message", equalTo("User already exists"));
    }

    @Test
    public void createUserWithoutEmailReturnException() {
        UserModel userWithoutEmail = new UserModel(null, user.getPassword(), user.getName());
        createUser(userWithoutEmail)
                .then()
                .statusCode(HTTP_FORBIDDEN)
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    public void createUserWithoutPasswordReturnException() {
        UserModel userWithoutEmail = new UserModel(user.getEmail(), null, user.getName());
        createUser(userWithoutEmail)
                .then()
                .statusCode(HTTP_FORBIDDEN)
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    public void createUserWithoutNameReturnException() {
        UserModel userWithoutEmail = new UserModel(user.getEmail(), user.getPassword(), null);
        createUser(userWithoutEmail)
                .then()
                .statusCode(HTTP_FORBIDDEN)
                .body("message", equalTo("Email, password and name are required fields"));
    }
}

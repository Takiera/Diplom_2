import model.UserModel;
import org.junit.Test;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;
import static org.hamcrest.CoreMatchers.equalTo;
import static steps.UserSteps.createUser;
import static steps.UserSteps.loginUser;

public class LoginUserTests extends BaseUserTest {

    @Test
    public void loginUserSuccess() {
        createUser(user);
        UserModel loginUserData = new UserModel(user.getEmail(), user.getPassword());
        loginUser(loginUserData)
                .then()
                .statusCode(HTTP_OK)
                .body("success", equalTo(true));
    }

    @Test
    public void loginUserWithWrongEmailReturnException() {
        createUser(user);
        UserModel loginUserDataWithWrongEmail = new UserModel("wrong email", user.getPassword());
        loginUser(loginUserDataWithWrongEmail)
                .then()
                .statusCode(HTTP_UNAUTHORIZED)
                .body("message", equalTo("email or password are incorrect"));
    }

    @Test
    public void loginUserWithWrongPasswordReturnException() {
        createUser(user);
        UserModel loginUserDataWithWrongPassword = new UserModel(user.getEmail(), "wrong password");
        loginUser(loginUserDataWithWrongPassword)
                .then()
                .statusCode(HTTP_UNAUTHORIZED)
                .body("message", equalTo("email or password are incorrect"));
    }
}

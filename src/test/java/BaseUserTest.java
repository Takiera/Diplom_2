import model.UserModel;
import org.junit.After;
import org.junit.Before;

import static data.UserData.*;
import static steps.UserSteps.deleteUser;

public class BaseUserTest extends BaseApiTest {

    protected UserModel user;

    @Before
    public void setUser() {
        user = new UserModel(EMAIL, PASSWORD, NAME);
    }

    @After
    public void tearDown() {
        if(user.getToken() != null) {
            deleteUser(user);
        }
    }
}

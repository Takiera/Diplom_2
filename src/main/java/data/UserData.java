package data;

public class UserData {

    public static final String BASE_URI = "https://stellarburgers.education-services.ru";

    public static final String EMAIL = "super_login" + System.currentTimeMillis() + "@yandex.ru";
    public static final String PASSWORD = "12345";
    public static final String NAME = "Oleg";

    public static final String CREATE_USER_PATH = "/api/auth/register";
    public static final String LOGIN_USER_PATH = "/api/auth/login";
    public static final String DELETE_USER_PATH = "/api/auth/user";

}
package api.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import helper.DataManager;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    protected Gson gson = new GsonBuilder().setPrettyPrinting().create();
    protected static String usersEndpoint;

    @BeforeAll
    public static void setUp() {
        usersEndpoint = DataManager.getUsersEndpoint();

    }
}

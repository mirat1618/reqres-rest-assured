package helper.factory;

import helper.DataManager;
import model.User;

import java.util.Map;

public class UserFactory {
    private static Map<String, User> users;

    static {
        users = DataManager.getUsers();
    }

    public static User getUser(String name) {
        return new User(users.get(name));
    }
}

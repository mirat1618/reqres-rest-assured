package helper.factory;

import helper.DataManager;
import model.User;

import java.util.Map;

public class UserFactory { // Фабрика для создания инстансов пользователей
    private static Map<String, User> users; // Map, хранящий пользователей из YAML-файлов

    static {
        users = DataManager.getUsers(); // Получаем пользователей
    }

    public static User getUser(String name) { // Основной метод для получения нового инстанса пользователя по имени
        return new User(users.get(name)); // С помощью конструктора копирования создаем новый инстанс и возвращаем его
    }
}

package helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DataManager { // Основной вспомогательный класс для считывания, хранения и получения данных из .properties и YAML-файлов
    private static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    private static Logger logger = LoggerFactory.getLogger(DataManager.class);
    private static Properties properties = new Properties();
    private static ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    private static TypeReference<HashMap<String, User>> usersMapTypeReference = new TypeReference<HashMap<String, User>>() { }; // Для создания Map с данным пользователей из users.yaml

    private static String restAssuredBaseURI;
    private static final String usersEndpoint;
    private static final Map<String, User> users;

    static {
        InputStream urlsPropertiesInputStream = ClassLoader.getSystemResourceAsStream("urls.properties");
        File usersYamlFile = new File(classLoader.getResource("users.yaml").getFile());

        readPropertiesFile(urlsPropertiesInputStream);

        users = readYamlFile(usersYamlFile, usersMapTypeReference);

        restAssuredBaseURI = properties.getProperty("baseUrl");
        usersEndpoint = properties.getProperty("usersEndpoint");
    }

    private static void readPropertiesFile(InputStream inputStream) { // Считываем эндпоинты из urls.properties
        try {
            properties.load(inputStream);
        } catch(IOException e) {
            logger.error("IOException while reading urls.properties file");
            e.printStackTrace();
        }
    }

    private static <T> Map<String, T> readYamlFile(File f, TypeReference<HashMap<String, T>> typeReference) {
        /* Считываем всех пользователей из YAML-файла user.yaml и помещаем их в Map
        * Метод можно переиспользовать для других YAML-файлов
        * */
        Map<String, T> map = null;
        try {
            map = mapper.readValue(f, typeReference);
        } catch(IOException e) {
            logger.error("IOException while reading yaml file");
            e.printStackTrace();
        }
        return map;
    }

    /* Getters */
    public static String getRestAssuredBaseURI() {
        return restAssuredBaseURI;
    }

    public static String getUsersEndpoint() {
        return usersEndpoint;
    }

    public static Map<String, User> getUsers() {
        return users;
    }
}

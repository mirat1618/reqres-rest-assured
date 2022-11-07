package helper;

import helper.enums.RequestType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static io.restassured.RestAssured.given;

public class RequestSender { // Вспомогательный класс для отправки HTTP/HTTPS-запросов
    private static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    private static Logger logger = LoggerFactory.getLogger(RequestSender.class);
    private static RequestSpecification baseRequestSpec;
    private static RequestSpecification usersRequestSpec;

    static {
        RequestSender.baseRequestSpec = given().baseUri(DataManager.getRestAssuredBaseURI()); // Базовая спецификация с основным хостом
        RequestSender.usersRequestSpec = given(baseRequestSpec).log().all().header("Content-Type", "application/json"); // Более конкретная спецификация для json-запросов
    }

    public static Response doRequest(RequestType requestType, String endpoint) { // Для отправки запросов GET, DELETE -- те, что без тела
        return doRequest(requestType, endpoint, null); // Без тела запроса
    }

    public static Response doRequest(RequestType requestType, String endpoint, String body) { // Отправка запросов
        switch (requestType) {
            case GET:
                return usersRequestSpec.get(endpoint).then().extract().response();
            case POST:
                return usersRequestSpec.body(body).post(endpoint).then().extract().response(); // С телом запроса
            case PUT:
                return usersRequestSpec.body(body).put(endpoint).then().extract().response(); // С телом запроса
            case PATCH:
                return usersRequestSpec.body(body).patch(endpoint).then().extract().response(); // С телом запроса
            case DELETE:
                return usersRequestSpec.delete(endpoint).then().extract().response();
            default:
                return null;
        }
    }
}

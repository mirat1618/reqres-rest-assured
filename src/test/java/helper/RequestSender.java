package helper;

import helper.enums.RequestType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static io.restassured.RestAssured.given;

public class RequestSender {
    private static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    private static Logger logger = LoggerFactory.getLogger(RequestSender.class);
    private static RequestSpecification baseRequestSpec;
    private static RequestSpecification usersRequestSpec;

    static {
        RequestSender.baseRequestSpec = given().baseUri(DataManager.getRestAssuredBaseURI());
        RequestSender.usersRequestSpec = given(baseRequestSpec).log().all().header("Content-Type", "application/json");
    }

    public static Response doRequest(RequestType requestType, String endpoint) {
        return doRequest(requestType, endpoint, null);
    }

    public static Response doRequest(RequestType requestType, String endpoint, String body) {
        switch (requestType) {
            case GET:
                return usersRequestSpec.get(endpoint).then().extract().response();
            case POST:
                return usersRequestSpec.body(body).post(endpoint).then().extract().response();
            case PUT:
                return usersRequestSpec.body(body).put(endpoint).then().extract().response();
            case PATCH:
                return usersRequestSpec.body(body).patch(endpoint).then().extract().response();
            case DELETE:
                return usersRequestSpec.delete(endpoint).then().extract().response();
            default:
                return null;
        }
    }
}

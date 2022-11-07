package api.test.creating;

import api.test.BaseTest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import helper.DataManager;
import helper.RequestSender;
import helper.enums.RequestType;
import helper.factory.UserFactory;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.Argument;
import model.User;
import org.apache.struts.chain.commands.servlet.CreateAction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Создание")
public class CreateTest extends BaseTest  {
    private final Logger logger = LoggerFactory.getLogger(CreateTest.class);
    private String testDisplayName;

    @BeforeEach
    public void init(TestInfo testInfo) {
        testDisplayName = testInfo.getDisplayName();
        logger.info(testDisplayName);
    }

    @DisplayName("Создание пользователей")
    @ParameterizedTest(name = "{1}")
    @MethodSource("usersDataProvider")
    public void createNikitaTest(User user, String name) {
        String json = gson.toJson(user);

        Response response = RequestSender.doRequest(RequestType.POST, usersEndpoint, json);

        logger.info(response.getBody().asPrettyString());

        assertThat(response.statusCode()).isEqualTo(201);
        assertThat(response.jsonPath().getString("id"))
                .withFailMessage("id отсутствует")
                .isNotNull();
    }

    private static Stream<Arguments> usersDataProvider() {
        User nikita = UserFactory.getUser("nikita");
        User finat = UserFactory.getUser("finat");

        return Stream.of(Arguments.of(nikita, "Никитос"),
                        Arguments.of(finat, "Финат")
        );
    }
}

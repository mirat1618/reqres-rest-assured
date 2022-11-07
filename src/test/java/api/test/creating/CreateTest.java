package api.test.creating;

import api.test.BaseTest;
import helper.RequestSender;
import helper.enums.RequestType;
import helper.factory.UserFactory;
import io.restassured.response.Response;
import model.User;
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
        testDisplayName = testInfo.getDisplayName(); // Получаем значением @DisplayName
        logger.info(testDisplayName);
    }

    @DisplayName("Создание пользователей")
    @ParameterizedTest(name = "{1}") // второй аргумент метода
    @MethodSource("usersDataProvider") // метод, предоставляющий тестовые данные
    public void createNikitaTest(User user, String name) {
        String json = gson.toJson(user); // Сериализируем полученных пользователей

        Response response = RequestSender.doRequest(RequestType.POST, usersEndpoint, json); // Отправляем POST-запрос с сериализированным пользователем

        logger.info(response.getBody().asPrettyString());

        assertThat(response.statusCode()).isEqualTo(201); // Проверяем статус ответа
        assertThat(response.jsonPath().getString("id"))
                .withFailMessage("id отсутствует")
                .isNotNull(); // Проверяем, что поле id было присвоено
    }

    private static Stream<Arguments> usersDataProvider() { // метод для предоставления данных
        // Создаем объекты по данным из YAML-файлов
        User nikita = UserFactory.getUser("nikita");
        User finat = UserFactory.getUser("finat");

        // Возвращаем созданные объекты
        return Stream.of(Arguments.of(nikita, "Никитос"),
                        Arguments.of(finat, "Финат")
        );
    }
}

package api.test.updating;

import api.test.BaseTest;
import api.test.creating.CreateTest;
import helper.RequestSender;
import helper.enums.RequestType;
import io.restassured.response.Response;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Удаление")
public class UpdateTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger(CreateTest.class);
    private String testDisplayName;

    @BeforeEach
    public void init(TestInfo testInfo) {
        testDisplayName = testInfo.getDisplayName();
        logger.info(testDisplayName);
    }

    @Test
    public void updateTest() {
        User morpheus = new User("2", "morpheus", "matrix qa automation engineer"); // Создаем объект пользователя
        String json = gson.toJson(morpheus); // Сериализируем

        Response response = RequestSender.doRequest(RequestType.PUT, usersEndpoint + "/" + morpheus.getId(), json); // Отправляем PUT-запрос с телом
        logger.info(response.getBody().asPrettyString());

        User returnedUser = response.jsonPath().getObject("", User.class); // Десериализируем полученный ответ

        assertThat(response.statusCode()).isEqualTo(200); // Проверяем возвращенный статус-код
        assertThat(morpheus).isEqualTo(returnedUser); // Убеждаемся, что возвращенный объект равен изначальному
    }
}

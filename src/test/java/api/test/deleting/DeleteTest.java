package api.test.deleting;

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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Удаление")
public class DeleteTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger(CreateTest.class);
    private String testDisplayName;

    @BeforeEach
    public void init(TestInfo testInfo) {
        testDisplayName = testInfo.getDisplayName();
        logger.info(testDisplayName);
    }

    @Test
    public void deleteTest() {
        Integer userIdToBeDeleted = 2;

        Response response = RequestSender.doRequest(RequestType.DELETE, usersEndpoint + "/" + userIdToBeDeleted);
        assertThat(response.statusCode()).isEqualTo(204);
    }
}

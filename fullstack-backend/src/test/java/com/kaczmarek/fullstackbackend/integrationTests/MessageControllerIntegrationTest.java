package com.kaczmarek.fullstackbackend.integrationTests;

import com.kaczmarek.fullstackbackend.FullstackBackendApplication;
import com.kaczmarek.fullstack.generated.model.MessageDto;
import com.kaczmarek.fullstack.generated.model.NewMessageDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(classes = FullstackBackendApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MessageControllerIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
        .withDatabaseName("testdb")
        .withUsername("test")
        .withPassword("test");

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;

    @BeforeAll
    static void setUpAll() {
    }

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/api";
    }

    @Nested
    class GetMessagesTest {

        @Test
        void shouldReturnEmptyListWhenNoMessages() {
            final var response = restTemplate.exchange(
                baseUrl + "/messages",
                HttpMethod.GET,
                null,
                List.class
            );

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

            final var body = response.getBody();
            assertThat(body).isNotNull();
            assertThat(body).hasSize(1);
            final var message = (Map<String, Object>) body.get(0);
            assertThat(message.get("content")).isEqualTo("&lt;b&gt;Integration Test&lt;/b&gt;");
            assertThat(message.get("id")).isEqualTo(1);
        }
    }

    @Nested
    class AddMessageTest {

        @Test
        void shouldAddMessageAndReturnDto() {
            final var newMessageDto = new NewMessageDto();
            newMessageDto.setContent("<b>Integration Test</b>");
            final var headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            final var request = new HttpEntity<>(newMessageDto, headers);

            final var response = restTemplate.postForEntity(
                baseUrl + "/messages",
                request,
                MessageDto.class
            );

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            MessageDto body = response.getBody();
            assertThat(body).isNotNull();
            assertThat(body.getContent()).contains("&lt;b&gt;Integration Test&lt;/b&gt;"); // sprawdzamy escape
            assertThat(body.getId()).isNotNull();
        }
    }

}
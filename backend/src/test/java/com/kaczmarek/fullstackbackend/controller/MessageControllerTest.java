package com.kaczmarek.fullstackbackend.controller;

import com.kaczmarek.fullstack.generated.model.MessageDto;
import com.kaczmarek.fullstackbackend.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.kaczmarek.fullstackbackend.TestDataFactory.createMessageDto;
import static com.kaczmarek.fullstackbackend.TestDataFactory.createNewMessageDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MessageControllerTest {

    @Mock
    private MessageService service;

    @InjectMocks
    private MessageController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class GetMessagesTest {

        @Test
        void shouldReturnListOfMessages() {
            final var messageDtos = List.of(
                createMessageDto(1L, "Hello"),
                createMessageDto(2L, "World")
            );
            when(service.getAll()).thenReturn(messageDtos);

            final ResponseEntity<List<MessageDto>> response = controller.getMessages();

            verify(service).getAll();
            assertThat(response).isNotNull();
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).hasSize(2);
            assertThat(response.getBody()).extracting(MessageDto::getContent).containsExactly("Hello", "World");
        }

        @Test
        void shouldReturnEmptyListWhenNoMessages() {
            when(service.getAll()).thenReturn(List.of());

            final ResponseEntity<List<MessageDto>> response = controller.getMessages();

            verify(service).getAll();
            assertThat(response.getBody()).isEmpty();
        }
    }

    @Nested
    class AddMessageTest {

        @Test
        void shouldReturnSavedMessageDto() {
            final var newMessageDto = createNewMessageDto("Test content");
            final var savedMessageDto = createMessageDto(1L, "Test content");

            when(service.save(newMessageDto)).thenReturn(savedMessageDto);

            final ResponseEntity<MessageDto> response = controller.addMessage(newMessageDto);

            verify(service).save(newMessageDto);
            assertThat(response).isNotNull();
            assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
            assertThat(response.getBody()).isEqualTo(savedMessageDto);
        }
    }


}

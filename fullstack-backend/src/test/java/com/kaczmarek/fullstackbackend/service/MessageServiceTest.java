package com.kaczmarek.fullstackbackend.service;

import com.kaczmarek.fullstack.generated.model.MessageDto;
import com.kaczmarek.fullstackbackend.mapper.MessageMapper;
import com.kaczmarek.fullstackbackend.model.Message;
import com.kaczmarek.fullstackbackend.repository.MessageRepository;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.kaczmarek.fullstackbackend.TestDataFactory.createMessage;
import static com.kaczmarek.fullstackbackend.TestDataFactory.createMessageDto;
import static com.kaczmarek.fullstackbackend.TestDataFactory.createNewMessageDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MessageServiceTest {

    @Mock
    private MessageRepository repository;

    @Mock
    private MessageMapper mapper;

    @InjectMocks
    private MessageService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class SaveTest {

        @Test
        void shouldEscapeHtmlContent() {
            final var newMessageDto = createNewMessageDto("<b>bold</b>");
            final var escapedContent = StringEscapeUtils.escapeHtml4(newMessageDto.getContent());
            final var messageToSave = createMessage(1L, escapedContent);
            final var messageDto = createMessageDto(1L, escapedContent);
            when(repository.save(any(Message.class))).thenReturn(messageToSave);
            when(mapper.toDto(messageToSave)).thenReturn(messageDto);

            final var result = service.save(newMessageDto);

            verify(repository).save(argThat(msg -> escapedContent.equals(msg.getContent())));
            assertThat(result).isNotNull();
            assertThat(result.getContent()).isEqualTo(escapedContent);
        }

        @Test
        void shouldEscapeHtmlContent_WhenScriptTag() {
            final var newMessageDto = createNewMessageDto("<script>alert('xss')</script>");
            final var escapedContent = StringEscapeUtils.escapeHtml4(newMessageDto.getContent());
            final var messageToSave = createMessage(2L, escapedContent);
            final var messageDto = createMessageDto(2L, escapedContent);
            when(repository.save(any(Message.class))).thenReturn(messageToSave);
            when(mapper.toDto(messageToSave)).thenReturn(messageDto);

            final var result = service.save(newMessageDto);

            verify(repository).save(argThat(msg -> escapedContent.equals(msg.getContent())));
            assertThat(result.getContent()).contains("&lt;script&gt;");
        }
    }

    @Nested
    class GetAllTest {

        @Test
        void shouldReturnEmptyList_WhenNoMessages() {
            when(repository.findAll()).thenReturn(List.of());
            when(mapper.toDtoList(List.of())).thenReturn(List.of());

            final var result = service.getAll();

            assertThat(result).isEmpty();
        }

        @Test
        void shouldReturnListOfMessageDtos() {
            final var messages = List.of(
                createMessage(1L, "Hello"),
                createMessage(2L, "World")
            );
            final var dtos = List.of(
                createMessageDto(1L, "Hello"),
                createMessageDto(2L, "World")
            );
            when(repository.findAll()).thenReturn(messages);
            when(mapper.toDtoList(messages)).thenReturn(dtos);

            final var result = service.getAll();

            assertThat(result).hasSize(2);
            assertThat(result).extracting(MessageDto::getContent).containsExactly("Hello", "World");
        }
    }

}
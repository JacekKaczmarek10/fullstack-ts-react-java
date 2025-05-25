package com.kaczmarek.fullstackbackend;


import com.kaczmarek.fullstack.generated.model.MessageDto;
import com.kaczmarek.fullstack.generated.model.NewMessageDto;
import com.kaczmarek.fullstackbackend.model.Message;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class TestDataFactory {

    public static MessageDto createMessageDto(final Long id, final String content) {
        final var dto = new MessageDto();
        dto.setId(id);
        dto.setContent(content);
        return dto;
    }

    public static NewMessageDto createNewMessageDto(final String content) {
        final var dto = new NewMessageDto();
        dto.setContent(content);
        return dto;
    }

    public static Message createMessage(final Long id, final String content) {
        final var message = new Message();
        message.setId(id);
        message.setContent(content);
        return message;
    }
}
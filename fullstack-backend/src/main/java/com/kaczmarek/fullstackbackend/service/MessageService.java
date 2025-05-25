package com.kaczmarek.fullstackbackend.service;

import com.kaczmarek.fullstack.generated.model.MessageDto;
import com.kaczmarek.fullstack.generated.model.NewMessageDto;
import com.kaczmarek.fullstackbackend.mapper.MessageMapper;
import com.kaczmarek.fullstackbackend.model.Message;
import com.kaczmarek.fullstackbackend.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    private final MessageMapper mapper;
    private final MessageRepository repository;

    public MessageDto save(NewMessageDto newMessageDto) {
        final var input = newMessageDto.getContent();
        final var sanitized = org.apache.commons.text.StringEscapeUtils.escapeHtml4(input);
        log.info("Saving message: {}", sanitized);
        var message = new Message();
        message.setContent(sanitized);
        message = repository.save(message);
        return mapper.toDto(message);
    }

    public List<MessageDto> getAll() {
        log.debug("Fetching all messages");
        final var messages = repository.findAll();
        return mapper.toDtoList(messages);
    }
}

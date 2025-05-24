package com.kaczmarek.fullstackbackend.service;

import com.kaczmarek.fullstackbackend.model.Message;
import com.kaczmarek.fullstackbackend.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository repository;

    public Message save(String input) {
        final var sanitized = StringEscapeUtils.escapeHtml4(input);
        final var message = new Message();
        message.setContent(sanitized);
        return repository.save(message);
    }

    public List<Message> getAll() {
        return repository.findAll();
    }
}
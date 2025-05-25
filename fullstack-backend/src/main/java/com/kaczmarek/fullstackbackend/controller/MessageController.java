package com.kaczmarek.fullstackbackend.controller;

import com.kaczmarek.fullstack.generated.api.ApiApi;
import com.kaczmarek.fullstack.generated.model.MessageDto;
import com.kaczmarek.fullstack.generated.model.NewMessageDto;
import com.kaczmarek.fullstackbackend.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/messages")
public class MessageController implements ApiApi {

    private final MessageService service;

    @Override
    public ResponseEntity<List<MessageDto>> getMessages() {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    public ResponseEntity<MessageDto> addMessage(NewMessageDto newMessageDto) {
        return ResponseEntity.ok(service.save(newMessageDto));
    }

}
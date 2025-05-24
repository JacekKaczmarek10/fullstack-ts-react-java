package com.kaczmarek.fullstackbackend.controller;

import com.kaczmarek.fullstackbackend.model.Message;
import com.kaczmarek.fullstackbackend.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService service;

    @PostMapping
    public Message create(@RequestBody Map<String, String> body) {
        String content = body.get("content");
        return service.save(content);
    }

    @GetMapping
    public List<Message> getAll() {
        return service.getAll();
    }
}
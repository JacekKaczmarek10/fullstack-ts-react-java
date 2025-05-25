package com.kaczmarek.fullstackbackend.mapper;

import com.kaczmarek.fullstack.generated.model.MessageDto;
import com.kaczmarek.fullstackbackend.model.Message;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    MessageDto toDto(Message entity);

    List<MessageDto> toDtoList(List<Message> entity);
}

package com.example.board.mapper;

import com.example.board.dto.CreateUserRequest;
import com.example.board.dto.RegisterUserRequest;
import com.example.board.dto.UpdateUserRequest;
import com.example.board.dto.UserResponse;
import com.example.board.model.User;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "posts",ignore = true)
    User toEntity(RegisterUserRequest dto);

    // UpdateRequest DTO -> Entity 업데이트
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "name",ignore = true)
    @Mapping(target = "posts", ignore = true)
    void updateEntityFromDto(UpdateUserRequest dto, @MappingTarget User entity);

    UserResponse toResponse(User user);
}

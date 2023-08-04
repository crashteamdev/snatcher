package dev.crashteam.snatcher.mapper;

import dev.crashteam.snatcher.model.domain.User;
import dev.crashteam.snatcher.model.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto>{
}

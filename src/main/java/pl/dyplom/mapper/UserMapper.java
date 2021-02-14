package pl.dyplom.mapper;

import org.mapstruct.Mapper;
import pl.dyplom.domain.User;
import pl.dyplom.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {


    UserDto toUserDto(User user);

    User toUser(UserDto userDto);

}

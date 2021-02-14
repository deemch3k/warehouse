package pl.dyplom.dto;

import lombok.Data;
import pl.dyplom.domain.UserRole;

@Data
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private UserRole role;

}

package pl.dyplom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.dyplom.domain.User;
import pl.dyplom.dto.UserDto;
import pl.dyplom.mapper.UserMapper;
import pl.dyplom.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> userList() {
        return userService.getList().stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public User getOne(@PathVariable("id") User user) {
        return user;
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto userDto) {
        User user = userMapper.toUser(userDto);
        User createdUser =  userService.createUser(user);
        return userMapper.toUserDto(createdUser);
    }

    @PutMapping("{id}")
    public UserDto update(@PathVariable("id") User userFromDB, @RequestBody UserDto userDto) {
        User user = userMapper.toUser(userDto);
        User updatedUser = userService.update(userFromDB, user);
        return userMapper.toUserDto(updatedUser);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") User user) {
        userService.delete(user);
    }
}
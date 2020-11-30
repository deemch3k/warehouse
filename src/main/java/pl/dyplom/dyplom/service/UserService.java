package pl.dyplom.dyplom.service;

import pl.dyplom.dyplom.domain.User;

import java.util.List;

public interface UserService {

    List<User> getList();
    User createUser(User user);
    User update(User userFromDB, User user);
    void delete(User user);
}

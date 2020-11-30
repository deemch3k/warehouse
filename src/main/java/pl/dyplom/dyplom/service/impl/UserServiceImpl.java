package pl.dyplom.dyplom.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.dyplom.dyplom.domain.User;
import pl.dyplom.dyplom.repo.UserRepo;
import pl.dyplom.dyplom.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userDetailsRepo) {
        this.userRepo = userDetailsRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public List<User> getList() {
        return userRepo.findAll();
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public User update(User userFromDB, User user) {
        BeanUtils.copyProperties(user, userFromDB);
        return userRepo.save(userFromDB);
    }

    public void delete(User user) {
        userRepo.delete(user);
    }
}
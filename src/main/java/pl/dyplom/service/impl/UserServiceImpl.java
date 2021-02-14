package pl.dyplom.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.dyplom.domain.User;
import pl.dyplom.repo.UserRepo;
import pl.dyplom.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userDetailsRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userDetailsRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public List<User> getList() {
        return userRepo.findAll();
    }

    public User createUser(User user) {
        String userPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(userPassword);
        return userRepo.save(user);
    }

    public User update(User userFromDB, User user) {
        BeanUtils.copyProperties(user, userFromDB);
        userFromDB.setPassword(passwordEncoder.encode(userFromDB.getPassword()));
        return userRepo.save(userFromDB);
    }

    public void delete(User user) {
        userRepo.delete(user);
    }
}

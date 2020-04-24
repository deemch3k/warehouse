package pl.dyplom.dyplom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dyplom.dyplom.repo.UserRepo;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userDetailsRepo) {
        this.userRepo = userDetailsRepo;
    }

}

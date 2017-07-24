package org.ababup1192.springdesign;


import org.ababup1192.springdesign.exception.AgeLimitException;
import org.ababup1192.springdesign.exception.InvalidNameException;
import org.ababup1192.springdesign.exception.UserNotFoundException;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> all() {
        return userRepository.all();
    }

    @Override
    public User signUp(User user) {
        if (user.getAge() < 15) {
            throw new AgeLimitException();
        } else if (!StringUtil.isFirstLetter(user.getName(), 'J')) {
            throw new InvalidNameException(user.getName().charAt(0));
        } else {
            userRepository.store(user);
            return user;
        }
    }

    @Override
    public User signIn(String name) {
        return userRepository.resolveBy(name).
                orElseThrow(() -> new UserNotFoundException(name));
    }
}

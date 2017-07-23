package org.ababup1192.springdesign;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final List<User> users;

    public UserRepositoryImpl() {
        users = new ArrayList<>();
    }

    @Override
    public List<User> all() {
        return users;
    }

    @Override
    public User store(User user) {
        users.add(user);
        return user;
    }

    @Override
    public Optional<User> resolveBy(String name) {
        return users.stream().filter(u -> u.getName().equals(name)).findFirst();
    }


}

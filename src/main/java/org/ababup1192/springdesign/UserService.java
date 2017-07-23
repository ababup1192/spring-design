package org.ababup1192.springdesign;

import java.util.List;

public interface UserService {
    List<User> all();

    User signUp(User user);

    User signIn(String name);
}

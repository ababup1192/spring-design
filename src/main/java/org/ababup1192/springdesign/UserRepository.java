package org.ababup1192.springdesign;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> all();

    User store(User user);

    Optional<User> resolveBy(String name);
}

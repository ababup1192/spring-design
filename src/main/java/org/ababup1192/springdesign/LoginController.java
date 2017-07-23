package org.ababup1192.springdesign;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LoginController {
    public List<User> users = new ArrayList<>();

    @GetMapping("/")
    public ResponseEntity<List<User>> findAllUser() {
        // Persistent + Response
        return ResponseEntity.ok(users);
    }

    @PostMapping("/sign_up")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        // Business Logic
        if (user.getAge() >= 15) {
            // Persistent
            users.add(user);
            // Response
            return ResponseEntity.ok("Register: " + user.getName());
        } else {
            // Response
            throw new AgeLimitException();
        }
    }

    @PostMapping("/sign_in")
    public ResponseEntity<String> signIn(@RequestBody String name) {
        // Persistent
        return users.stream().filter(u -> u.getName().equals(name)).findFirst().
                        // Response
                        map(u -> ResponseEntity.ok(u.getName())).
                        orElseThrow(() -> new UserNotFoundException(name));
    }
}

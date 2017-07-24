package org.ababup1192.springdesign;

import org.ababup1192.springdesign.exception.AgeLimitException;
import org.ababup1192.springdesign.exception.InvalidNameException;
import org.ababup1192.springdesign.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LoginController {
    private List<User> users = new ArrayList<>();

    @GetMapping("/")
    public ResponseEntity<List<User>> findAllUser() {
        // Persistent + Response
        return ResponseEntity.ok(users);
    }

    @PostMapping("/sign_up")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        System.out.println(isFirstLetter(user.getName(), 'J'));
        // Business Logic
        if (user.getAge() < 15) {
            throw new AgeLimitException();
        } else if (!isFirstLetter(user.getName(), 'J')) {
            throw new InvalidNameException(user.getName().charAt(0));
        } else {
            // Persistent
            users.add(user);
            // Response
            return ResponseEntity.ok("Register: " + user.getName());
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

    private boolean isFirstLetter(String str, char firstLetter) {
        return str.charAt(0) == firstLetter;
    }
}

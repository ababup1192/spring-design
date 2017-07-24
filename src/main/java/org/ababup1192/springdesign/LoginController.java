package org.ababup1192.springdesign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {
    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> findAllUser() {
        return ResponseEntity.ok(userService.all());
    }

    @PostMapping("/sign_up")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        final User registeredUser = userService.signUp(user);
        return ResponseEntity.ok("Register: " + registeredUser.getName());
    }

    @PostMapping("/sign_in")
    public ResponseEntity<String> signIn(@RequestBody String name) {
        final User signedInUser = userService.signIn(name);
        return ResponseEntity.ok(signedInUser.getName());
    }
}

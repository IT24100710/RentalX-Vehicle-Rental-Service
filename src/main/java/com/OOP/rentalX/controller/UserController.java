package com.OOP.rentalX.controller;

import com.OOP.rentalX.model.User;
import com.OOP.rentalX.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service = new UserService();

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        service.register(user);
        return "User registered!";
    }

    @PostMapping("/login")
    public User login(@RequestParam String userId, @RequestParam String password) {
        return service.login(userId, password);
    }

    @GetMapping("/profile/{id}")
    public User profile(@PathVariable String id) {
        return service.getProfile(id);
    }

    @PutMapping("/update")
    public String update(@RequestBody User user) {
        service.updateProfile(user);
        return "User updated!";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.deleteProfile(id);
        return "User deleted!";
    }
}

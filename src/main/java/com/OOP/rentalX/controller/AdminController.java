package com.OOP.rentalX.controller;

import com.OOP.rentalX.model.Admin;
import com.OOP.rentalX.service.AdminService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService service = new AdminService();

    @PostMapping("/login")
    public Admin login(@RequestParam String adminId, @RequestParam String password) {
        return service.login(adminId, password);
    }

    @PostMapping("/register")
    public String register(@RequestBody Admin admin) {
        service.register(admin);
        return "Admin registered.";
    }
}

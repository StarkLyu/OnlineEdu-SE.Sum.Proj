package com.se231.onlineedu.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController Class
 *
 * this controller only for authorization test
 *
 * @author Yuxuan Liu
 *
 * @date 2019/07/01
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPER_ADMIN')")
    public String userAccess() {
        return "User Contents";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
    public String projectManagementAccess() {
        return "Admin Contents";
    }

    @GetMapping("/superAdmin")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public String adminAccess() {
        return "Super Admin Contents";
    }
}
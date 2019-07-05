package com.se231.onlineedu.message.request;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.*;

/**
 * SignUpForm Class
 *
 * Sign up request form
 *
 * @author Yuxuan Liu
 *
 * @date 2019/07/01
 */
public class SignUpForm {
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    private List<String> roles;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public SignUpForm(@NotBlank @Size(min = 3, max = 50) String username, List<String> roles, @NotBlank @Size(min = 6, max = 40) String password) {
        this.username = username;
        this.roles = roles;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRole() {
        return roles;
    }

    public void setRole(List<String> role) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
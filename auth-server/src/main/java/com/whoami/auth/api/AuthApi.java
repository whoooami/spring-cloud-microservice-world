package com.whoami.auth.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by whoami on 17-7-19.
 */
@RestController
//@RequestMapping(value = "/auth")
public class AuthApi {

    @GetMapping(value = "/user")
    public Principal user(Principal user) {
        return user;
    }
}

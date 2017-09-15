package com.whoami.auth.security;

import com.google.common.collect.Lists;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by whoami on 17-7-19.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("Whoami")) {
            return new User("Whoami", "123456", Lists.newArrayList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        } else if (username.equals("nothing")) {
            return new User("nothing", "123456", Lists.newArrayList(new SimpleGrantedAuthority("ROLE_USER")));
        }
        return null;
    }
}

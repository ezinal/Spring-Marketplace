package com.tr.obss.jss.jss_marketplace.service.impl;

import com.tr.obss.jss.jss_marketplace.model.User;
import com.tr.obss.jss.jss_marketplace.security.MyUserDetails;
import com.tr.obss.jss.jss_marketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userService.findByUsername(username);

        return MyUserDetails.build(user);
    }
}


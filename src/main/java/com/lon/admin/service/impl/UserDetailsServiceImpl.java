package com.lon.admin.service.impl;

import com.lon.admin.model.LoginUser;
import com.lon.admin.model.entity.User;
import com.lon.admin.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final IUserService userService;

    public UserDetailsServiceImpl(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.loadUserByUsername(username);

        // TODO 查询用户权限及角色
        Set<String> permissions = new HashSet<>();
        permissions.add("system:user:list");

        Set<String> roles = new HashSet<>();
        roles.add("system:user:list");

        return new LoginUser(user, permissions, roles);
    }
}

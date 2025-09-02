package com.hexaware.carrentalsystems.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hexaware.carrentalsystems.entities.User;

public class UserInfoUserDetails implements UserDetails {

    //private String name;
    private String password;
    private List<GrantedAuthority> authorities;
	private String email;

    public UserInfoUserDetails(User user) {
       // this.name = user.getName();
        this.email=user.getEmail();
        this.password = user.getPassword();
        this.authorities = Arrays.stream(user.getRole().split(","))
                                 .map(SimpleGrantedAuthority::new)
                                 .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // customize if you track expiry
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // customize if you track lock status
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // customize if you track credentials expiry
    }

    @Override
    public boolean isEnabled() {
        return true; // customize if you track enabled status
    }
}

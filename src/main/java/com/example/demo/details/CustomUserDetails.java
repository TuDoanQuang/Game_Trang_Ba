package com.example.demo.details;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.enums.Role;
import com.example.demo.model.User;

public class CustomUserDetails implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
     
    public CustomUserDetails(User user) {
        this.user = user;
    }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		if ("admin".equals(user.getEmail()) || "admin".equals(user.getUserName())) {
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.name()));
		} else {
			authorities.add(new SimpleGrantedAuthority(Role.USER.name()));
		}
         
        return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}

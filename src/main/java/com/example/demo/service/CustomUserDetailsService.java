package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.details.CustomUserDetails;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
    private UserRepository userRepo;
    
	//private static Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	if ("admin".equals(username)) {
    		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode("123");
    		User admin = new User();
    		admin.setEmail("admin");
    		admin.setPassword(encodedPassword);
    		admin.setUserName("admin");
    		return new CustomUserDetails(admin);
    	}else {
    		User user = userRepo.findByEmail(username);
    		if (user == null) {
    			throw new UsernameNotFoundException("User not found");
    		}
    		return new CustomUserDetails(user);
    	}
    	
        
    }
}

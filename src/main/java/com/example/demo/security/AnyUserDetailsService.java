package com.example.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Service
public class AnyUserDetailsService implements UserDetailsService{
	
	private final static String ROLE_TAG = "ROLE_USER";

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.selectByUsername(username);
		if(user==null){
			throw new UsernameNotFoundException("用户不存在！");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(ROLE_TAG));
		UserPrincipal userPrincipal = new UserPrincipal(username,user.getPassword(),authorities);
		userPrincipal.setNickName(user.getNickName());
		return userPrincipal;
	}

}

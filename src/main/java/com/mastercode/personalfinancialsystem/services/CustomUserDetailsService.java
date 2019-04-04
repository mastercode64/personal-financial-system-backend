package com.mastercode.personalfinancialsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mastercode.personalfinancialsystem.domain.User;
import com.mastercode.personalfinancialsystem.domain.UserSecurityDetails;
import com.mastercode.personalfinancialsystem.respository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);

		if (user == null)
			throw new UsernameNotFoundException("User not found.");

		return new UserSecurityDetails(user);
	}
	
	public Object userSession() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
package com.mastercode.personalfinancialsystem.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mastercode.personalfinancialsystem.domain.User;
import com.mastercode.personalfinancialsystem.domain.UserSecurityDetails;
import com.mastercode.personalfinancialsystem.dto.UserDTO;
import com.mastercode.personalfinancialsystem.exception.ResourceNotFoundException;
import com.mastercode.personalfinancialsystem.exception.ValidationErrorException;
import com.mastercode.personalfinancialsystem.respository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User findById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found"));
	}

	public Page<User> findAll(Integer page, Integer size) {
		Pageable pageable = PageRequest.of(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(10));
		Page<User> userPage = userRepository.findAll(pageable);
		return userPage;
	}

	public User create(UserDTO userDto) {		
		if (userEmailExists(userDto.getEmail()))
			throw new ValidationErrorException("Email: " + userDto.getEmail() + " already exists!");
		
		@Valid User user = new User(userDto.getName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()));
		return userRepository.save(user);
	}

	public User update(UserDTO userDto, Long userId) {
		User user = this.findById(userId);
		user = this.mergeUserFields(user, userDto);		
		return userRepository.save(user);
	}

	private User mergeUserFields(User user, UserDTO dto) {		
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));		
		return user;
	}

	public void delete(Long id) {
		userRepository.delete(this.findById(id));
	}
	
	public User getUserFromSession() {
		UserSecurityDetails userDetails = null;
		try {
			userDetails = (UserSecurityDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return userDetails.getUser();
		} catch (Exception ex) {
			throw new AuthenticationCredentialsNotFoundException("User is not authenticated");
		}
	}

	private boolean userEmailExists(String email) {
		if (userRepository.findByEmail(email) == null)
			return false;
		else
			return true;
	}

}

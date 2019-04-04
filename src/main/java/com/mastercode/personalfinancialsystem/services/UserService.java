package com.mastercode.personalfinancialsystem.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mastercode.personalfinancialsystem.domain.User;
import com.mastercode.personalfinancialsystem.domain.UserSecurityDetails;
import com.mastercode.personalfinancialsystem.dto.UserDTO;
import com.mastercode.personalfinancialsystem.exception.ResourceNotFoundException;
import com.mastercode.personalfinancialsystem.exception.UniqueFieldException;
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

	public User create(@Valid User user) {		
		if (userEmailExists(user.getEmail()))
			throw new UniqueFieldException("Email: " + user.getEmail() + " already exists!");
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User update(UserDTO userDto, Long userId) {
		User oldUser = this.findById(userId);
		this.updateUserFields(oldUser, userDto.dtoToUser());
		return userRepository.save(oldUser);
	}

	private void updateUserFields(User oldUser, User newUser) {
		oldUser.setName(newUser.getName() == null ? oldUser.getName() : newUser.getName());
		oldUser.setEmail(newUser.getEmail() == null ? oldUser.getEmail() : newUser.getEmail());
		oldUser.setPassword(newUser.getPassword() == null ? oldUser.getPassword() : newUser.getPassword());
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
			ex.printStackTrace();
			return null;
		}
	}

	private boolean userEmailExists(String email) {
		if (userRepository.findByEmail(email) == null)
			return false;
		else
			return true;
	}

}

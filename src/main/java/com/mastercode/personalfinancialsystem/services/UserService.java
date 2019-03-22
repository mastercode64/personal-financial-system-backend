package com.mastercode.personalfinancialsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mastercode.personalfinancialsystem.domain.User;
import com.mastercode.personalfinancialsystem.exception.ResourceNotFoundException;
import com.mastercode.personalfinancialsystem.respository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found"));
	}

	public Page<User> findAll(Integer page, Integer size) {
		page = page == null ? 0 : page;
		size = size == null ? 10 : size;

		Pageable pageable = PageRequest.of(page, size);
		Page<User> userPage = userRepository.findAll(pageable);
		return userPage;
	}
	
	public User create(User user) {
		return userRepository.save(user);
	}
	
	public User update(User user) {
		User managed = this.findById(user.getId());
		this.updateUserFields(managed, user);
		return userRepository.save(managed);
	}
	
	private void updateUserFields(User managed, User newUser) {
		managed.setNome(newUser.getNome() == null ? managed.getNome() : newUser.getNome());
		managed.setEmail(newUser.getEmail() == null ? managed.getEmail() : newUser.getEmail());
	}
	
	public void delete(Long id) {		
		userRepository.delete(this.findById(id));
	}
}

package com.isa.web.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.web.data.model.User;
import com.isa.web.data.repository.UserRepository;
import com.isa.web.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findById(Long id) {
		LOG.info("Entered findById() method with: {}", id);
		return userRepository.findOne(id);
	}
	
	@Override
	public User findByUsername(String username) {
		LOG.info("Entered findByName() method with: {}", username);
		return userRepository.findByUsername(username);
	}

	@Override
	public void saveUser(User user) {
		LOG.info("Entered saveUser() method with: {}", user);
		userRepository.save(user);
	}

	@Override
	public void updateUser(User user) {
		LOG.info("Entered updateUser() method with: {}", user);
		saveUser(user);
	}

	@Override
	public void deleteUserById(Long id) {
		LOG.info("Entered deleteUserById() method with: {}", id);
		userRepository.delete(id);
	}

	@Override
	public void deleteAllUsers() {
		LOG.info("Entered deleteAllUsers() method");
		userRepository.deleteAll();
	}

	@Override
	public List<User> findAllUsers() {
		LOG.info("Entered findAllUsers() method");
		return userRepository.findAll();
	}

	@Override
	public boolean isUserExist(User user) {
		LOG.info("Entered isUserExist() method with: {}", user);
		return findByUsername(user.getUsername()) != null;
	}
}

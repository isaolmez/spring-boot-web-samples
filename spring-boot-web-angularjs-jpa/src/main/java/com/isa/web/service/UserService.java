package com.isa.web.service;

import java.util.List;

import com.isa.web.data.model.User;

public interface UserService {
	User findById(Long id);

	User findByUsername(String username);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserById(Long id);

	void deleteAllUsers();

	List<User> findAllUsers();

	boolean isUserExist(User user);

}

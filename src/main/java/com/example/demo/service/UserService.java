package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Track;
import com.example.demo.model.User;

public interface UserService {
	public void addRegister(User user);


	public User findByEmailIdAndPassword(String emailId, String password) throws UserNotFoundException;


	User updatePassword(User user, String id);


}

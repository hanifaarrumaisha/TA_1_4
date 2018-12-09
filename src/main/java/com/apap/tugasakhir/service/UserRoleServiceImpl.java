package com.apap.tugasakhir.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apap.tugasakhir.model.UserRoleModel;
import com.apap.tugasakhir.repository.UserRoleDb;  

@Service
public class UserRoleServiceImpl implements UserRoleService{
	@Autowired
	private UserRoleDb userDb;
	
	@Override
	public UserRoleModel addUser(UserRoleModel user) {
		String pass = encrypt(user.getPassword());
		user.setPassword(pass);
		return userDb.save(user);
	}
	
	@Override
	public String encrypt(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	@Override
	public boolean checkIfValidOldPassword(UserRoleModel user, String oldPassword) {
		System.out.println("password: "+user.getPassword());
		System.out.println("password input: "+oldPassword);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(oldPassword, user.getPassword());
	}

	@Override
	public UserRoleModel findUserByUsername(String username) {
		return userDb.findByUsername(username);
	}

	@Override
	public void changeUserPassword(UserRoleModel user, String password) {
	    String encryptPass = encrypt(password);
	    user.setPassword(encryptPass);
		userDb.save(user);
	}
	
	@Override
	public boolean validatePassword(String password) {
		System.out.println(password);
		return true;
	}

	@Override
	public boolean validateNewPassword(String password, String confirmPassword) {
		return password.equals(confirmPassword);
	}
}

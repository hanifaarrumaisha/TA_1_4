package com.apap.tugasakhir.service;

import com.apap.tugasakhir.model.UserRoleModel;

public interface UserRoleService {
	UserRoleModel addUser(UserRoleModel user);
	public String encrypt(String password);
	UserRoleModel findUserByUsername(String name);
	boolean checkIfValidOldPassword(UserRoleModel user, String oldPassword);
	void changeUserPassword(UserRoleModel user, String password);
	boolean validatePassword(String password);
	boolean validateNewPassword(String password, String confirmPassword);
}

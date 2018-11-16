package com.apap.tugasakhir.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.apap.tugasakhir.repository.UserRoleDb;
public class UserRoleServiceImpl implements UserRoleService {
	
	@Autowired
	private UserRoleDb userDb;
}

package com.apap.tugasakhir.repository;

import org.springframework.stereotype.Repository;

import com.apap.tugasakhir.model.UserRoleModel;

@Repository
public interface UserRoleDb {
	UserRoleModel findByUsername(String username);
}

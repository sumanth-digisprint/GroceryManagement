package com.groceryManagement.adminService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.groceryManagement.adminService.model.Admin;

public interface AdminRepository extends MongoRepository<Admin, String> {

	Admin findByEmail(String email);

	Admin findByEmailAndPassword(String email, String password);
}

package com.ayush.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ayush.entities.User;

public interface UserRepo extends CrudRepository<User, Integer>{

	@Query(value="SELECT u.*\r\n"
			+ "FROM users u\r\n"
			+ "JOIN designation d ON u.designation_id = d.designation_id\r\n"
			+ "WHERE d.designation_name = 'Customer'", nativeQuery=true)
	public List<User> findAllCustomers();
	
	@Query(value="SELECT u.*\r\n"
			+ "FROM users u\r\n"
			+ "JOIN designations d ON u.designation_id = d.designation_id\r\n"
			+ "WHERE d.designation IN ('Customer', 'Department_User')", nativeQuery=true)
	public List<User> findAllDepartmentCustomer();
	
	
	@Query(value="SELECT u.*\r\n"
			+ "FROM users u\r\n"
			+ "JOIN designations d ON u.designation_id = d.designation_id\r\n"
			+ "WHERE d.designation IN ('Customer', 'Department_User', 'Department_Head')", nativeQuery=true)
	public List<User> findAllDepartmentHead();
	
}

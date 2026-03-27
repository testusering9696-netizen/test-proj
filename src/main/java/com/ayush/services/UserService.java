package com.ayush.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayush.entities.Designation;
import com.ayush.entities.Role;
import com.ayush.entities.User;
import com.ayush.repos.DesignationRepo;
import com.ayush.repos.RoleRepo;
import com.ayush.repos.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo repo1;
	
	@Autowired
	private RoleRepo repo2;
	
	@Autowired
	private DesignationRepo repo3;
	
	public List<User> dialectReq(Integer userId){
		
		Optional<User> opt = repo1.findById(userId);
		User user = null;
		
		if(opt.isPresent())
			 user = opt.get();
		
		ArrayList<User> list = null;
		
		if(user.getDesignation().getDesignation().equals("Customer")) {
			list.add(user);
		}else if(user.getDesignation().getDesignation().equals("Department_User")) {

			list = (ArrayList<User>)showDepartmentUserData();
			list.add(user);
		}
		else if(user.getDesignation().getDesignation().equals("Department_Head")) {
			
			list = (ArrayList<User>)showDepartmentHeadData();
			list.add(user);
		}
		else if(user.getDesignation().getDesignation().equals("Director")) {
			list = (ArrayList<User>)showDirectorData();
			list.add(user);
		}
		
		
		return list;
	}
	
	
	public List<User> showDepartmentUserData(){
		ArrayList<User> list = (ArrayList<User>)repo1.findAllCustomers();
	
		return list;
	}
	
	public List<User> showDepartmentHeadData() { 
		
		ArrayList<User> list= (ArrayList<User>)repo1.findAllDepartmentCustomer();
		
		return list;
	}
	
	public List<User> showDirectorData() {
		
		ArrayList<User> list = (ArrayList<User>)repo1.findAllDepartmentHead();
		
		
		return list;
	}
	
	public void saveUser(String name , Integer age , String email , String role , String designation) {
		
		Role r = new Role();
		r.setRole(role);
		
		Designation d = new Designation();
		
		d.setDesignation(designation);
		
		
		
		User user = new User();
		user.setDesignation(d);
		user.setRole(r);
		user.setAge(age);
		user.setEmail(email);
		user.setName(name);

		
		repo3.save(d);
		repo2.save(r);
		repo1.save(user);
	}
	
	
}

package com.ayush.rests;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ayush.entities.User;
import com.ayush.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user") 
public class UserRestController {

	@Autowired
	private UserService service; 
	
	@GetMapping("/login")
	public List<User> login(@RequestParam("id") Integer userId, String email) {
		
		List<User> list = service.dialectReq(userId);
		
		return list;
	}
	
	@GetMapping("/add")
	public String addUser( HttpServletRequest request,@RequestParam("nm") String name , @RequestParam("ag") Integer age,@RequestParam("em") String email) {
		
		String role = request.getHeader("role");
		
		String designation = request.getHeader("designation");
		
		
		service.saveUser(name , age , email , role , designation);
		
		
		return "You Have Been Registered";
	}
	

	
}

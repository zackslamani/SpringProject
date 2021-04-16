package com.quest.etna.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.quest.etna.model.Address;
import com.quest.etna.model.User;
import com.quest.etna.repository.UserRepository;
import com.quest.etna.service.UserService;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {


	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="")
	@ResponseStatus(HttpStatus.OK)
	public List <User> getListUser(){
		 
		List<User> users = userService.getList();

		 return users;
	}
	
	
	
	
	@GetMapping(value="/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
		User user = userService.getOneById(id);
	
		if(user == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody User user) {
		return userService.create(user);
	}
	
	@PutMapping("/{id}")
	  public User updateUser(@PathVariable("id") long id, @RequestBody User user) {
	    Optional<User> userData = userRepository.findById(id);
	    	return userService.update(id, user);
	    }
	
	
	  @DeleteMapping("/{id}")
	  @PreAuthorize("hasRole('ADMIN')")
	  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
	   
	      Boolean success = userService.delete(id);
	      if(success) {
	    	  return new ResponseEntity<>(HttpStatus.OK);
	      }
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    
	  }
	
	
}

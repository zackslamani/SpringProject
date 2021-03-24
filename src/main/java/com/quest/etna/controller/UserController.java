package com.quest.etna.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.quest.etna.model.User;
import com.quest.etna.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/us")
public class UserController {


	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(value="/user")
	public List <User> getListUser(){
		 
		List<User> users = new ArrayList<User>();
		      
		userRepository.findAll().forEach(users::add);

		 return users;
	}
	
	@GetMapping(value="/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
	Optional <User> user = userRepository.findById(id);
	
		if(user.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(user.get(),HttpStatus.OK);
	}
	
	@PostMapping("/user")
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody User user) {
		userRepository.save(user);
		return user;
	}
	
	@PutMapping("/user/{id}")
	  public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
	    Optional<User> userData = userRepository.findById(id);

	    if (userData.isPresent()) {
	      User _user = userData.get();
	      _user.setPassword(user.getPassword());
	      _user.setUsername(user.getUsername());
	      _user.setRoles(user.getRoles());
	      return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	    }
	
	
	  @DeleteMapping("/users/{id}")
	  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
	    try {
	      userRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	
	
}

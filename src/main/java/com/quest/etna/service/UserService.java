package com.quest.etna.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quest.etna.model.Address;
import com.quest.etna.model.User;
import com.quest.etna.repository.UserRepository;



@Service
public class UserService implements IModelService<User>{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getList() {
		 
		List<User> users = new ArrayList<User>();
			      
		userRepository.findAll().forEach(users::add);

		return users;
	}

	@Override
	public User getOneById(Long id) {
		Optional <User> user = userRepository.findById(id);
		
		if(user.isEmpty()) {
			return null;
		}
		return user.get();
	}

	@Override
	public User create(User entity) {
		userRepository.save(entity);
		return entity;
	}

	@Override
	public User update(Long id, User entity) {
	Optional <User> user = userRepository.findById(id);
		
		if(user.isEmpty()) {
			return null;
		}
		
		User userFound = user.get();
		
		userFound.setUsername(entity.getUsername());
		userFound.setAddresses(entity.getAddresses());
		userFound.setRoles(entity.getRoles());
		
		userRepository.save(userFound);
		
		return userFound;
	}

	@Override
	public Boolean delete(Long id) {
		try {
			Optional <User> user = userRepository.findById(id);
			
			if(user.isEmpty()) {
				return null;
			}
			User userFound = user.get();
			
			userRepository.delete(userFound);
		} catch (Exception e) {
			return false;
		}
		return null;
	}

	
}

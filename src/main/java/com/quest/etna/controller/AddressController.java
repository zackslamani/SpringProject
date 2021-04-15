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

import com.quest.etna.model.Address;
import com.quest.etna.repository.AddressRepository;
import com.quest.etna.service.AddressService;
@CrossOrigin(origins = "http://localhost:8090", maxAge = 3600)
@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AddressService addressService;
	
	
	@GetMapping(value="")
	@ResponseStatus(HttpStatus.OK)
	public List <Address> getListAddress(){
		 
		List<Address> addresses = addressService.getList();

		 return addresses;
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Address> getAddressById(@PathVariable("id") Long id){
		Address address = addressService.getOneById(id);
	
		if(address == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(address,HttpStatus.OK);
	}
	
	
	
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Address createAddress(@RequestBody Address address) {
		return addressService.create(address);
	}
	
	@GetMapping(value="/address/user/{id}")
	public List<Address> getAddressByUserId(@PathVariable("id") Long id){
	List<Address> address = addressRepository.findByUserId(id);
	
		return address;
	}

	
	@PutMapping("/{id}")
	  public Address updateAddress(@PathVariable("id") long id, @RequestBody Address address) {
	    Optional<Address> addressData = addressRepository.findById(id);
	    	return addressService.update(id, address);
	    }
	
	
	  @DeleteMapping("/{id}")
	  public ResponseEntity<HttpStatus> deleteAddress(@PathVariable("id") long id) {
	   
	      Boolean success = addressService.delete(id);
	      if(success) {
	    	  return new ResponseEntity<>(HttpStatus.OK);
	      }
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    
	  }
	
	

}

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
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/ad")
public class AddressController {

	@Autowired
	private AddressRepository addressRepository;
	
	@GetMapping(value="/addresses")
	public List <Address> getListAddress(){
		 
		List<Address> addresses = new ArrayList<Address>();
		      
		addressRepository.findAll().forEach(addresses::add);

		 return addresses;
	}
	
	@GetMapping(value="/address/{id}")
	public ResponseEntity<Address> getAddressById(@PathVariable("id") Long id){
	Optional <Address> address = addressRepository.findById(id);
	
		if(address.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(address.get(),HttpStatus.OK);
	}
	
	@PostMapping("/address")
	@ResponseStatus(HttpStatus.CREATED)
	public Address createAddress(@RequestBody Address address) {
		addressRepository.save(address);
		return address;
	}
	
	@GetMapping(value="/address/user/{id}")
	public List<Address> getAddressByUserId(@PathVariable("id") Long id){
	List<Address> address = addressRepository.findByUserId(id);
	
		return address;
	}

	
	@PutMapping("/address/{id}")
	  public ResponseEntity<Address> updateAddress(@PathVariable("id") long id, @RequestBody Address address) {
	    Optional<Address> addressData = addressRepository.findById(id);

	    if (addressData.isPresent()) {
	      Address _address = addressData.get();
	      _address.setCity(address.getCity());
	      _address.setStreet(address.getStreet());
	      _address.setPostalCode(address.getPostalCode());
	      _address.setCountry(address.getCountry());
	      return new ResponseEntity<>(addressRepository.save(_address), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	    }
	
	
	  @DeleteMapping("/addresses/{id}")
	  public ResponseEntity<HttpStatus> deleteAddress(@PathVariable("id") long id) {
	    try {
	      addressRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	

}

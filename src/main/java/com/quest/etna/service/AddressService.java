package com.quest.etna.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quest.etna.model.Address;
import com.quest.etna.repository.AddressRepository;

@Service
public class AddressService implements IModelService<Address>{

	@Autowired
	private AddressRepository addressRepository;
	
	
	@Override
	public List<Address> getList() {
		List<Address> addresses = new ArrayList<Address>();
	      
		addressRepository.findAll().forEach(addresses::add);

		 return addresses;
	}

	@Override
	public Address getOneById(Long id) {
		
		Optional <Address> address = addressRepository.findById(id);
		
		if(address.isEmpty()) {
			return null;
		}
		return address.get();
	}

	@Override
	public Address create(Address entity) {
		addressRepository.save(entity);
		return entity;
	}

	@Override
	public Address update(Long id, Address entity) {
	Optional <Address> address = addressRepository.findById(id);
		
		if(address.isEmpty()) {
			return null;
		}
		
		Address addressFound = address.get();
		
		addressFound.setStreet(entity.getStreet());
		addressFound.setCity(entity.getCity());
		addressFound.setCountry(entity.getCountry());
		addressFound.setPostalCode(entity.getPostalCode());
		
		addressRepository.save(addressFound);
		
		return addressFound;
	}

	@Override
	public Boolean delete(Long id) {
		try {
			Optional <Address> address = addressRepository.findById(id);
			
			if(address.isEmpty()) {
				return null;
			}
			Address addressFound = address.get();
			
			addressRepository.delete(addressFound);
		} catch (Exception e) {
			return false;
		}

		return true;
	}


}

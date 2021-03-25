package com.quest.etna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.quest.etna.config.JwtUserDetailsService;
import com.quest.etna.model.Address;
import com.quest.etna.model.User;
import com.quest.etna.service.AddressService;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestAddressController {
	
	@Autowired
	protected MockMvc mockMvc;
	
	@Autowired
	protected JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	protected AddressService addressService;
	
	@Test
	protected void getListAddress() throws Exception {
	
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/addresses"))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	
	@Test
	protected void getAddressById() throws Exception {
		
		//Address newAddress = new Address("street", "postalCode", "city", "country", "user", "creationDate", "updatedDate")
		Address newAddress = new Address("testJUnitstreet", "testJUnitpostalCode", "testJUnitcity", "testJUnitcountry");
		newAddress = addressService.create(newAddress);
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/addresses/"+newAddress.getId()))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.street").value(newAddress.getStreet()));
		
	}
	
	/*
	@Test
	protected void createAddress() {
		
	}
	
	
	@Test
	protected void getAddressByUserId() {
		
	}
	
	
	@Test
	protected void updateAddress() {
		
	}
	
	
	@Test
	protected void deleteAddress() {
		
	}*/

}

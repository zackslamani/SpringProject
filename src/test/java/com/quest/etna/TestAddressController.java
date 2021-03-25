package com.quest.etna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.quest.etna.config.JwtUserDetailsService;
import com.quest.etna.model.Address;
import com.quest.etna.model.ERole;
import com.quest.etna.model.Role;
import com.quest.etna.model.User;
import com.quest.etna.service.AddressService;
import com.quest.etna.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
	
	@Autowired
	protected UserService userService;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	/*@Test
	protected void getListAddress() throws Exception {
	
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/address"))
		.andDo(print())
		.andExpect(status().isUnauthorized());
	}*/
	
	@Test
	protected void testAuthenticate() throws Exception {
		
		
		
	    User user = new User();
	    user.setUsername("us");
	    user.setPassword("pass");
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(user);

	    mockMvc.perform(post("/register").contentType(APPLICATION_JSON_UTF8)
	        .content(requestJson))
		.andDo(print())
		.andExpect(status().isCreated());
	      

	}
	
	/*
	@Test
	protected void createAddress() throws Exception {
		
		//Address newAddress = new Address("street", "postalCode", "city", "country", "user", "creationDate", "updatedDate")
		Address newAddress = new Address("testJUnitstreet", "testJUnitpostalCode", "testJUnitcity", "testJUnitcountry");
		newAddress = addressService.create(newAddress);
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/addresses/"+newAddress.getId()))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.street").value(newAddress.getStreet()))
		.andExpect(jsonPath("$.city").value(newAddress.getCity()))
		.andExpect(jsonPath("$.country").value(newAddress.getCountry()))
		.andExpect(jsonPath("$.postalCode").value(newAddress.getPostalCode()));
		
	}
	
	
	@Test
	protected void testAuthenticate() throws Exception {
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/register"))
		.andDo(print())
		.andExpect(status().isCreated());
		
	}
	*/

}

package com.quest.etna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
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
	
	@Autowired
	private ObjectMapper objectMapper;

	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	User user = new User("foo", "foo");
	
	String jsonUser = asJsonString(user);
	
	private static String Bearer = "Bearer ";
	
	private static String accessToken ;
	
	private static List<String> roles;
	
	/*@Test
	protected void getListAddress() throws Exception {
	
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/address"))
		.andDo(print())
		.andExpect(status().isUnauthorized());
	}*/
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	} 
	
	@Test
	protected void testAuthenticate() throws Exception {
		
		//System.out.println(json);
		ResultActions resultActionsRegister = this.mockMvc
		.perform( MockMvcRequestBuilders
			      .post("/register")
			      .content(jsonUser)
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON));
			      //.andExpect(status().isCreated())
		
		MvcResult resultRegister = resultActionsRegister.andReturn();
		int statutRegister = resultRegister.getResponse().getStatus();
		//System.out.println(statutRegister);
		
		if(statutRegister == 201) {
			resultActionsRegister.andExpect(status().isCreated());
		}else if(statutRegister == 409) {
			resultActionsRegister.andExpect(status().isConflict());
		}
		
		
		//String jsonAuthenticate = asJsonString(new User("JunittdGest", "1223434"));
		//System.out.println(json);
		ResultActions resultActionsAuthenticate = this.mockMvc
		.perform( MockMvcRequestBuilders
			      .post("/authenticate")
			      .content(jsonUser)
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
				
		
		MvcResult resultAuthenticate = resultActionsAuthenticate.andReturn();
		int statutAuthenticate = resultAuthenticate.getResponse().getStatus();
		MockHttpServletResponse contentAuthenticate =  resultAuthenticate.getResponse();
		//String [] words = contentAuthenticate.split(",");
		//accessToken = StringUtils.substring(words[3], 15, words[3].length() - 1);
		//String accessToken = words[3].substring(15,-1);
		//System.out.println(words[3]);
		
		//String roleString = StringUtils.substring(words[2], 9, words[2].length() - 1);
		//System.out.println("depuis testAuthenticate" + accessToken);
		//System.out.println(statutAuthenticate);
		//System.out.println(roleString);
		//System.out.println(accessToken);
		//System.out.println(contentAuthenticate);
		String str = contentAuthenticate.getContentAsString();
	
		
		//Response response = objectMapper.readValue(str, Response.class);
		
		//System.out.println(response.getUsername());
		
		
	}
	
	@Test
	protected void testUser() throws Exception {
		
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

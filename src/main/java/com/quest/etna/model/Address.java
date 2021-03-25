


package com.quest.etna.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="adresse")
@JsonIdentityInfo(
	   generator = ObjectIdGenerators.PropertyGenerator.class, 
	   property = "id")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 100)
	private String street;
	
	@NotBlank
	@Size(max = 30)
	private String postalCode;
	
	@NotBlank
	@Size(max = 50)
	private String city;
	
	@NotBlank
	@Size(max = 50)	
	private String country;
	
	
    @ManyToOne()
    @JsonBackReference
    @JoinTable(	name = "user_addresses", 
	joinColumns = @JoinColumn(name = "address_id"), 
	inverseJoinColumns = @JoinColumn(name = "user_id"))
    //@JsonIgnore
    private User user;
    
	private Date creationDate;
	
	private Date updatedDate;
	
	public Address() {
		
	}
	
	public Address(String street, String postalCode, String city, String country, User user, Date creationDate, Date updatedDate) {
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
		this.user = user;
		this.creationDate = creationDate;
		this.updatedDate = updatedDate;
	}
	
	public Address(String street, String postalCode, String city, String country) {
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public User getUser() {
	    return user;
	 }

	public void setUser(User user) {
	    this.user = user;
	}
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	

}



package com.quest.etna.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quest.etna.model.Address;



@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	//@Query("SELECT * FROM adresse")
	public List<Address> findAll();
	
	//@Query("SELECT * FROM adresse WHERE id = %id%")
	public Optional<Address> findById(Long id);
	
}

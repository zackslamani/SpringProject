

package com.quest.etna.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.quest.etna.model.Address;



@Repository
public interface AddressRepository extends PagingAndSortingRepository<Address, Long>, JpaRepository<Address, Long>{
	//@Query("SELECT * FROM adresse")
	public List<Address> findAll();
	
	//@Query("SELECT * FROM adresse WHERE id = %id%")
	public Optional<Address> findById(Long id);
	
	@Query(value = "select * from adresse a " +
	        "left join user_addresses ua " +
	        "   on a.id = ua.address_id " +
	        "left join users u " +
	        "   on ua.user_id = u.id " +
	        "where u.id = :id", nativeQuery = true)
		public List<Address> findByUserId(Long id);

}

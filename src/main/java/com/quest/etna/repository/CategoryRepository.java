package com.quest.etna.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quest.etna.model.Address;
import com.quest.etna.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	//@Query("SELECT * FROM adresse")
	public List<Category> findAll();
	
	//@Query("SELECT * FROM adresse WHERE id = %id%")
	public Optional<Category> findById(Long id);
}

package com.quest.etna.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quest.etna.model.Address;
import com.quest.etna.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	//@Query("SELECT * FROM adresse")
	public List<Comment> findAll();
	
	//@Query("SELECT * FROM adresse WHERE id = %id%")
	public Optional<Comment> findById(Long id);
}

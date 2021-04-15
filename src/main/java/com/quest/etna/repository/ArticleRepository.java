package com.quest.etna.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quest.etna.model.Address;
import com.quest.etna.model.Article;
import com.quest.etna.model.User;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	
	public List<Article> findAll();
	
	public Optional<Article> findById(Long id);
}

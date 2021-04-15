package com.quest.etna.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quest.etna.model.Address;
import com.quest.etna.model.Article;
import com.quest.etna.repository.AddressRepository;
import com.quest.etna.repository.ArticleRepository;

@Service
public class ArticleService implements IModelService<Article> {
	@Autowired
	private ArticleRepository articleRepository;
	
	@Override
	public List<Article> getList() {
		List<Article> articles = new ArrayList<Article>();
	      
		articleRepository.findAll().forEach(articles::add);

		 return articles;
	}

	@Override
	public Article getOneById(Long id) {
		
		Optional <Article> article = articleRepository.findById(id);
		
		if(article.isEmpty()) {
			return null;
		}
		return article.get();
	}

	@Override
	public Article create(Article entity) {
		articleRepository.save(entity);
		return entity;
	}

	@Override
	public Article update(Long id, Article entity) {
	Optional <Article> article = articleRepository.findById(id);
		
		if(article.isEmpty()) {
			return null;
		}
		
		Article articleFound = article.get();
		
		articleFound.setTitle(entity.getTitle());
		articleFound.setCategory(entity.getCategory());
		articleFound.setContent(entity.getContent());
		articleFound.setImage(entity.getImage());
		articleFound.setCreatedAt(entity.getCreatedAt());
		
		articleRepository.save(articleFound);
		
		return articleFound;
	}

	@Override
	public Boolean delete(Long id) {
		try {
			Optional <Article> article = articleRepository.findById(id);
			
			if(article.isEmpty()) {
				return null;
			}
			Article articleFound = article.get();
			
			articleRepository.delete(articleFound);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}

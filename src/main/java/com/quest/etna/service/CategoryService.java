package com.quest.etna.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quest.etna.model.Article;
import com.quest.etna.model.Category;
import com.quest.etna.model.Comment;
import com.quest.etna.repository.CategoryRepository;

@Service
public class CategoryService implements IModelService<Category>{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getList() {
		List<Category> category = new ArrayList<Category>();
	      
		categoryRepository.findAll().forEach(category::add);

		 return category;
	}

	@Override
	public Category getOneById(Long id) {
		
		Optional <Category> category = categoryRepository.findById(id);
		
		if(category.isEmpty()) {
			return null;
		}
		return category.get();
	}

	@Override
	public Category create(Category entity) {
		categoryRepository.save(entity);
		return entity;
	}

	@Override
	public Category update(Long id, Category entity) {
	Optional <Category> category = categoryRepository.findById(id);
		
		if(category.isEmpty()) {
			return null;
		}
		
		Category categoryFound = category.get();
		
		categoryFound.setTitle(entity.getTitle());
		categoryFound.setDescription(entity.getDescription());
		categoryFound.setArticles(entity.getArticles());
		
		categoryRepository.save(categoryFound);
		
		return categoryFound;
	}

	@Override
	public Boolean delete(Long id) {
		try {
			Optional<Category> category = categoryRepository.findById(id);
			
			if(category.isEmpty()) {
				return null;
			}
			Category categoryFound = category.get();
			
			categoryRepository.delete(categoryFound);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}

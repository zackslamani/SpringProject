package com.quest.etna.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.quest.etna.model.Category;
import com.quest.etna.repository.CategoryRepository;
import com.quest.etna.service.CategoryService;

@CrossOrigin(origins = "http://localhost:8090", maxAge = 3600)
@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping(value="")
	@ResponseStatus(HttpStatus.OK)
	public List <Category> getListCategory(){
		 
		List<Category> categorys = categoryService.getList();

		 return categorys;
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id){
		Category category = categoryService.getOneById(id);
	
		if(category == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(category,HttpStatus.OK);
	}
	
	
	
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Category createCategory(@RequestBody Category category) {
		return categoryService.create(category);
	}

	
	@PutMapping("/{id}")
	  public Category updateCategory(@PathVariable("id") long id, @RequestBody Category category) {
	    Optional<Category> categoryData = categoryRepository.findById(id);
	    	return categoryService.update(id, category);
	    }
	
	
	  @DeleteMapping("/{id}")
	  public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") long id) {
	   
	      Boolean success = categoryService.delete(id);
	      if(success) {
	    	  return new ResponseEntity<>(HttpStatus.OK);
	      }
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    
	  }
	
	
}

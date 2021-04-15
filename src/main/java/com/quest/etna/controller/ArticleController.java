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

import com.quest.etna.model.Article;
import com.quest.etna.repository.ArticleRepository;
import com.quest.etna.service.ArticleService;

@CrossOrigin(origins = "http://localhost:8090", maxAge = 3600)
@RestController
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private ArticleService articleService;
	
	
	@GetMapping(value="")
	@ResponseStatus(HttpStatus.OK)
	public List <Article> getListArticle(){
		 
		List<Article> articles = articleService.getList();

		 return articles;
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Article> getArticleById(@PathVariable("id") Long id){
		Article article = articleService.getOneById(id);
	
		if(article == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(article,HttpStatus.OK);
	}
	
	
	
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Article createArticle(@RequestBody Article article) {
		return articleService.create(article);
	}

	
	@PutMapping("/{id}")
	  public Article updateArticle(@PathVariable("id") long id, @RequestBody Article article) {
	    Optional<Article> articleData = articleRepository.findById(id);
	    	return articleService.update(id, article);
	    }
	
	
	  @DeleteMapping("/{id}")
	  public ResponseEntity<HttpStatus> deleteArticle(@PathVariable("id") long id) {
	   
	      Boolean success = articleService.delete(id);
	      if(success) {
	    	  return new ResponseEntity<>(HttpStatus.OK);
	      }
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    
	  }
	
	

}

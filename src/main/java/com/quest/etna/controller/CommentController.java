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

import com.quest.etna.model.Comment;
import com.quest.etna.repository.CommentRepository;
import com.quest.etna.service.CommentService;

@CrossOrigin(origins = "http://localhost:8090", maxAge = 3600)
@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private CommentService commentService;
	
	
	@GetMapping(value="")
	@ResponseStatus(HttpStatus.OK)
	public List <Comment> getListComment(){
		 
		List<Comment> comments = commentService.getList();

		 return comments;
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Comment> getCommentById(@PathVariable("id") Long id){
		Comment comment = commentService.getOneById(id);
	
		if(comment == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(comment,HttpStatus.OK);
	}
	
	
	
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Comment createComment(@RequestBody Comment comment) {
		return commentService.create(comment);
	}

	
	@PutMapping("/{id}")
	  public Comment updateComment(@PathVariable("id") long id, @RequestBody Comment comment) {
	    Optional<Comment> commentData = commentRepository.findById(id);
	    	return commentService.update(id, comment);
	    }
	
	
	  @DeleteMapping("/{id}")
	  public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id) {
	   
	      Boolean success = commentService.delete(id);
	      if(success) {
	    	  return new ResponseEntity<>(HttpStatus.OK);
	      }
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    
	  }
}

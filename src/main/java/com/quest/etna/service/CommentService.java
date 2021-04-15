package com.quest.etna.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quest.etna.model.Article;
import com.quest.etna.model.Category;
import com.quest.etna.model.Comment;
import com.quest.etna.repository.CommentRepository;

@Service
public class CommentService implements IModelService<Comment> {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public List<Comment> getList() {
		List<Comment> comment = new ArrayList<Comment>();
	      
		commentRepository.findAll().forEach(comment::add);

		 return comment;
	}

	@Override
	public Comment getOneById(Long id) {
		
		Optional <Comment> comment = commentRepository.findById(id);
		
		if(comment.isEmpty()) {
			return null;
		}
		return comment.get();
	}

	@Override
	public Comment create(Comment entity) {
		commentRepository.save(entity);
		return entity;
	}

	@Override
	public Comment update(Long id, Comment entity) {
	Optional <Comment> comment = commentRepository.findById(id);
		
		if(comment.isEmpty()) {
			return null;
		}
		
		Comment commentFound = comment.get();
		
		commentFound.setAuthor(entity.getAuthor());
		commentFound.setContent(entity.getContent());
		commentFound.setArticle(entity.getArticle());
		
		commentRepository.save(commentFound);
		
		return commentFound;
	}

	@Override
	public Boolean delete(Long id) {
		try {
			Optional <Comment> comment = commentRepository.findById(id);
			
			if(comment.isEmpty()) {
				return null;
			}
			Comment commentFound = comment.get();
			
			commentRepository.delete(commentFound);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}

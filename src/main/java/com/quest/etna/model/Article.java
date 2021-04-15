package com.quest.etna.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="Articles")
@JsonIdentityInfo(
	   generator = ObjectIdGenerators.PropertyGenerator.class, 
	   property = "id")
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 255)
	private String title;

	//@JsonIgnore
	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@NotBlank
	@Size(max = 255)
	private String image;

	@Temporal(TemporalType.DATE)
    Date createdAt;
	
    @ManyToOne()
    @JsonBackReference
    @JoinTable(	name = "category_articles", 
	joinColumns = @JoinColumn(name = "article_id"), 
	inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Category category;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(	name = "article_comments", 
				joinColumns = @JoinColumn(name = "article_id"), 
				inverseJoinColumns = @JoinColumn(name = "comment_id"))// inverse side: it has a mappedBy attribute, and can't decide how the association is mapped, since the other side already decided it.
	@JsonManagedReference
	private Set<Comment> comments = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}

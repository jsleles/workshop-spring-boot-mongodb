package com.nelioalves.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.repository.PostRepository;
import com.nelioalves.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	PostRepository postRepository;
	
	public Post findById (String id) {
		Optional<Post> post = postRepository.findById(id);
		return post.orElseThrow( ()-> new ObjectNotFoundException("Objeto não encontrato"));
	}
	
	public List<Post> findByTitle (String text) {
		return postRepository.searchTitle(text);
	}
	

}

package com.nelioalves.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.resources.util.URL;
import com.nelioalves.workshopmongo.service.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

	@Autowired
	PostService postService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Post> findByid (@PathVariable String id) {
		Post post = postService.findById(id);
		return ResponseEntity.ok().body(post);
		
	}	
	
	@GetMapping(value="/titlesearch")
	public ResponseEntity<List<Post>> findByTitle (@RequestParam(value="text",defaultValue = "") String text ) {
		text = URL.decodeParam(text);
		List<Post> list = postService.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/fullsearch")
	public ResponseEntity<List<Post>> fullSearch ( 
	@RequestParam(value="text",defaultValue = "") String text,
	@RequestParam(value="minDate",defaultValue = "") String minDate,
	@RequestParam(value="maxDate",defaultValue = "") String maxDate ) {
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<Post> list = postService.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
	
	
	
	
}

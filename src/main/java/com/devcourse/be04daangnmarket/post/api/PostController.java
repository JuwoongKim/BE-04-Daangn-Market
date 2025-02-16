package com.devcourse.be04daangnmarket.post.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.devcourse.be04daangnmarket.post.application.PostService;
import com.devcourse.be04daangnmarket.post.dto.PostRequest;
import com.devcourse.be04daangnmarket.post.dto.PostResponse;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

	public PostController(PostService postService) {
		this.postService = postService;
	}

	private final PostService postService;

	@PostMapping
	public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest request) {
		PostResponse response = postService.create(request.title(), request.description()
			, request.price(), request.views(), request.transactionType(), request.category(), request.status());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
		PostResponse response = postService.getPost(id);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Page<PostResponse>> getAllPost(@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "0") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<PostResponse> responses = postService.getAllPost(pageable);

		return new ResponseEntity<>(responses, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PostResponse> updatePost(@PathVariable Long id, @RequestBody PostRequest request) {
		PostResponse response = postService.update(id, request.title(), request.description()
			, request.price(), request.views(), request.transactionType(), request.category(), request.status());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable Long id) {
		postService.delete(id);
		return ResponseEntity.noContent().build();
	}

}

package com.gustavooarantes.post_api.controller;

import com.gustavooarantes.post_api.dto.PostRequestDTO;
import com.gustavooarantes.post_api.dto.PostResponseDTO;
import com.gustavooarantes.post_api.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@Tag(name = "Posts", description = "Posts endpoints.")
public class PostController {

  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  @Operation(summary = "List all posts", description = "Return a list of all posts. If there are no posts, return 204 (No Content).", responses = {
      @ApiResponse(responseCode = "200", description = "Posts successfully retrieved."),
      @ApiResponse(responseCode = "204", description = "No posts found.")
  })
  @GetMapping
  public ResponseEntity<List<PostResponseDTO>> getAllPosts() {
    List<PostResponseDTO> posts = service.getAllPosts();

    if (posts.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(posts);
  }

  @Operation(summary = "Search post by ID", description = "Return the details of a post, specified by its unique ID.", responses = {
      @ApiResponse(responseCode = "200", description = "Post successfully found."),
      @ApiResponse(responseCode = "404", description = "Post not found with specified ID.")
  })
  @GetMapping("/{id}")
  public ResponseEntity<PostResponseDTO> getPost(@PathVariable Long id) {
    return ResponseEntity.ok(service.getPostById(id));
  }

  @Operation(summary = "Create new post.", description = "Creates a new post.", responses = {
      @ApiResponse(responseCode = "201", description = "Post successfully created.")
  })
  @PostMapping
  public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO dto) {
    PostResponseDTO response = service.createPost(dto);
    return ResponseEntity.status(201).body(response);
  }

  @Operation(summary = "Update a blog post by ID", description = "Updates an existing post identified by its ID.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Post successfully updated."),
      @ApiResponse(responseCode = "404", description = "Post not found with specified ID.")
  })
  @PutMapping("/{id}")
  public ResponseEntity<PostResponseDTO> updatePost(
      @PathVariable Long id,
      @RequestBody PostRequestDTO dto) {
    return ResponseEntity.ok(service.updatePost(id, dto));
  }

  @Operation(summary = "Delete a blog post by ID", description = "Deletes an existing post identified by its ID.")
  @ApiResponses({
      @ApiResponse(responseCode = "204", description = "Post successfully deleted."),
      @ApiResponse(responseCode = "404", description = "Post not found with specified ID.")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePost(@PathVariable Long id) {
    service.deletePost(id);
    return ResponseEntity.noContent().build();
  }
}

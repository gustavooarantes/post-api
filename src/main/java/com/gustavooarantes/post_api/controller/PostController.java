package com.gustavooarantes.post_api.controller;

import com.gustavooarantes.post_api.dto.PostRequestDTO;
import com.gustavooarantes.post_api.dto.PostResponseDTO;
import com.gustavooarantes.post_api.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
}

package com.gustavooarantes.post_api.service;

import com.gustavooarantes.post_api.dto.PostRequestDTO;
import com.gustavooarantes.post_api.dto.PostResponseDTO;
import com.gustavooarantes.post_api.exception.PostNotFoundException;
import com.gustavooarantes.post_api.model.Post;
import com.gustavooarantes.post_api.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

  private final PostRepository repository;

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public PostResponseDTO getPostById(Long id) {
    Post post = repository.findById(id)
        .orElseThrow(() -> new PostNotFoundException(id));

    return new PostResponseDTO(
        post.getId(),
        post.getTitle(),
        post.getContent(),
        post.getCreatedAt(),
        post.getUpdatedAt());
  }

  public PostResponseDTO createPost(PostRequestDTO dto) {
    Post post = Post.builder()
        .title(dto.title())
        .content(dto.content())
        .build();

    Post saved = repository.save(post);

    return new PostResponseDTO(
        saved.getId(),
        saved.getTitle(),
        saved.getContent(),
        saved.getCreatedAt(),
        saved.getUpdatedAt());
  }
}

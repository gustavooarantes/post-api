package com.gustavooarantes.post_api.repository;

import com.gustavooarantes.post_api.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}

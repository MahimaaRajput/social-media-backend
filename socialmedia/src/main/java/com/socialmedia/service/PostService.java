package com.socialmedia.service;

import com.socialmedia.model.Post;

import java.util.List;

public interface PostService {
    Post createPost(Post post,Integer userId) throws Exception;
    String deletePost(Integer postId,Integer userId) throws Exception;
    List<Post> findPostByUserId(Integer userId);
    Post findPostById(Integer postId) throws Exception;
    List<Post> findAllPost();
    Post savedPost(Integer postId,Integer userId) throws Exception;
    Post likedPost(Integer postId,Integer userId) throws Exception;

}

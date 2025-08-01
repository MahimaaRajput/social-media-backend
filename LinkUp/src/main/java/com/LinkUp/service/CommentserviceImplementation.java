package com.LinkUp.service;

import com.LinkUp.model.Comment;
import com.LinkUp.model.Post;
import com.LinkUp.model.User;
import com.LinkUp.repository.CommentRepository;
import com.LinkUp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentserviceImplementation implements CommentService{
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userid) throws Exception {
        User user=userService.findUserById(userid);
        Post post=postService.findPostById(postId);
        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        Comment savedComment=commentRepository.save(comment);
        post.getComments().add(savedComment);
        postRepository.save(post);
        return savedComment;

    }
    @Override
    public Comment findCommentById(Integer commentId) throws Exception {
        Optional<Comment> opt=commentRepository.findById(commentId);
        if(opt.isEmpty())
        {
            throw new Exception("comment not exist with this id");
        }
        return opt.get();
    }

    @Override
    public Comment likeComment(Integer commentId, Integer userId) throws Exception {
        User user=userService.findUserById(userId);
        Comment comment=findCommentById(commentId);
        if(!comment.getLiked().contains(user))
        {
            comment.getLiked().add(user);
        }
        else comment.getLiked().remove(user);
        return commentRepository.save(comment);
    }


}

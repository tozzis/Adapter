package com.sit.cloudnative.PostService.Comment;

import java.util.List;

import com.sit.cloudnative.PostService.Post.Post;
import com.sit.cloudnative.PostService.Post.PostService;
import com.sit.cloudnative.PostService.User.User;
import com.sit.cloudnative.PostService.User.UserAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

  @Autowired
  private CommentService commentService;
  
  @Autowired
  private PostService postService;

  @Autowired 
  private UserAdapter userAdapter;

  // show comment in post by id
  @GetMapping("/post/{postId}/comment")
  public ResponseEntity<List<Comment>> getCommentByPostId(@PathVariable long postId){
    List<Comment> comment = commentService.getByPostId(postId);
    return new ResponseEntity<List<Comment>>(comment,HttpStatus.OK);
  }

  // new comment
  @PostMapping("/post/{postId}/comment/{userId}")
  public ResponseEntity<Comment> createComment(@PathVariable long postId,@PathVariable long userId,@RequestBody Comment comment){
    Post post = postService.getPostById(postId);
    User user = userAdapter.getUserDetail(userId);
    comment.setPost(post);
    comment.setUser(user);
    commentService.createComment(comment);
    return new ResponseEntity<Comment>(comment,HttpStatus.OK);
  }

   // delete comment by comment_id
   @DeleteMapping("/comment/{id}")
   public ResponseEntity<Long> deleteComment(@PathVariable Long id) {
    commentService.deleteComment(id);
     return new ResponseEntity<Long>(id,HttpStatus.OK);
   }
}
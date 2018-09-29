package com.sit.cloudnative.PostService.Post;

import java.util.List;

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
public class PostController {
  @Autowired
  private PostService postService;

  @Autowired
  private UserAdapter userAdapter;

  // show posts all
  @GetMapping("/post")
  public ResponseEntity<List<Post>> getAllPostList() {
    List<Post> post = postService.getAllPost();
    return new ResponseEntity<>(post,HttpStatus.OK);
  }

  // show post by post_id
  @GetMapping("/post/{postId}")
  public ResponseEntity<Post> getPostByIds(@PathVariable long postId) {
    Post post = postService.getPostById(postId);
    return new ResponseEntity<Post>(post,HttpStatus.OK);
  }

  // show posts by user_id
  @GetMapping("/post/{userId}/user")
  public ResponseEntity<List<Post>> getPostByUser(@PathVariable long userId) {
    List<Post> post = postService.getPostByUser(userId);
    return new ResponseEntity<List<Post>>(post,HttpStatus.OK);
  }

  // new post 
  @PostMapping("/post/{userId}")
  public ResponseEntity<Post> NewPost(@PathVariable long userId,@RequestBody Post post) {
    User user = userAdapter.getUserDetail(userId);
    post.setUser(user);
    postService.savePost(post);
    return new ResponseEntity<Post>(post,HttpStatus.OK);
  }

  // delete post by post_id
  @DeleteMapping("/post/{id}")
  public ResponseEntity<Long> deletePost(@PathVariable Long id) {
    postService.deletePost(id);
    return new ResponseEntity<Long>(id,HttpStatus.OK);
  }
  
}
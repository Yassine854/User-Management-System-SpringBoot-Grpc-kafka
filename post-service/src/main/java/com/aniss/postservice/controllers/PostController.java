package com.aniss.postservice.controllers;

import com.aniss.postservice.clients.IUserClient;
import com.aniss.postservice.entities.Post;
import com.aniss.postservice.models.User;
import com.aniss.postservice.requests.CreatePost;
import com.aniss.postservice.services.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private IPostService postService;
    @Autowired
    private IUserClient userClient;


    @GetMapping
    public ModelAndView getAllPosts(Model model) {
        List<Post> posts = postService.GetAllPosts();
        List<User> users = userClient.getAll();
        model.addAttribute("posts", posts);
        model.addAttribute("users", users);
        return new ModelAndView("posts");
    }

    @GetMapping("/{id}")
    public HttpEntity<Post> one(@PathVariable(name = "id") Long id)
    {
        Optional<Post> post = postService.one(id);
        return post.<HttpEntity<Post>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ModelAndView createPost(@RequestParam("user") String userId,
                             @RequestParam("content") String content,
                             Model model) {
        // Create a new Post object with the provided userId and content
        CreatePost createPost = new CreatePost();
        createPost.setUser(Long.parseLong(userId));
        createPost.setContent(content);

        // Call your service method to create the post
        Post post = postService.create(createPost);

        // Add any model attributes needed for the view, for example, if you want to display a success message
        model.addAttribute("message", "Post created successfully!");
        List<Post> posts = postService.GetAllPosts();
        List<User> users = userClient.getAll();
        model.addAttribute("posts", posts);
        model.addAttribute("users", users);

        // Redirect to the posts view
        return new ModelAndView("posts");
    }


}

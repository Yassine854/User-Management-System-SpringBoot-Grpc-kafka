package com.aniss.postservice.controllers;

import com.aniss.postservice.clients.IUserClient;
import com.aniss.postservice.entities.Post;
import com.aniss.postservice.models.User;
import com.aniss.postservice.services.IPostService;
import io.grpc.StatusRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserPostController {

    @Autowired
    private IPostService postService;

    @Autowired
    private IUserClient userClient;

    @GetMapping
    public ModelAndView getAllUsers(Model model) {
        List<User> users = userClient.getAll();
        model.addAttribute("users", users);
        return new ModelAndView("users");
    }

    @GetMapping("/{user}/posts")
    public ModelAndView all(@PathVariable(name = "user") Long user) {
        try {
            User userModel = userClient.getUserById(user);
            List<Post> posts = postService.all(userModel);
            ModelAndView modelAndView = new ModelAndView("posts");
            modelAndView.addObject("posts", posts);
            return modelAndView;
        } catch (StatusRuntimeException exception) {
            // Handle exception
            return new ModelAndView("error"); // Return error view or handle appropriately
        }
    }


}

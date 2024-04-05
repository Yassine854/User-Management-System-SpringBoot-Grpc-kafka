package com.aniss.userservice.controllers;

import com.aniss.userservice.KafkaProducer;
import com.aniss.userservice.entities.User;
import com.aniss.userservice.requests.CreateUser;
import com.aniss.userservice.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping
    public ModelAndView getAllUsers(Model model) {
        List<User> users = userService.GetAllUsers();
        model.addAttribute("users", users);
        return new ModelAndView("users");
    }

    @PostMapping
    public ModelAndView createUser(@RequestParam("name") String name,
                                   Model model) {
        // Create a new Post object with the provided userId and content
        CreateUser createuser = new CreateUser();
        createuser.setName(name);

        // Call your service method to create the post
        User user = userService.create(createuser);

        // Add any model attributes needed for the view, for example, if you want to display a success message
        model.addAttribute("message", "User created successfully!");
        List<User> users = userService.GetAllUsers();
        model.addAttribute("users", users);

        // Redirect to the posts view
        return new ModelAndView("users");
    }

    @GetMapping("/{id}")
    public HttpEntity<User> one(@PathVariable(name = "id") Long id)
    {
        Optional<User> user = userService.get(id);
        if ( user.isEmpty() )
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.get());
    }

    @PostMapping("/{id}")
    public ModelAndView deletePost(@PathVariable Long id, Model model) {
        Optional<User> user = userService.get(id);
        if (user.isPresent()) {
            userService.delete(user.get());
            List<User> users = userService.GetAllUsers();
            model.addAttribute("users", users);
            String message = "User " + user.get().getName() + " deleted";
            kafkaProducer.sendMessage("user_deleted", message);
            model.addAttribute("message", message); // Pass message to the view
            return new ModelAndView("users");
        }
        return new ModelAndView("users");
    }




}

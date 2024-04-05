package com.aniss.postservice;

import com.aniss.postservice.entities.Post;
import com.aniss.postservice.requests.CreatePost;
import com.aniss.postservice.services.IPostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PostServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(IPostService postService) {
		return args -> {
			// Create posts
			CreatePost post1 = new CreatePost();
			post1.setUser(1L);
			post1.setContent("c'est le post 1");

			CreatePost post2 = new CreatePost();
			post2.setUser(1L);
			post2.setContent("c'est le post 2");

			CreatePost post3 = new CreatePost();
			post3.setUser(1L);
			post3.setContent("c'est le post 3");


			CreatePost post4 = new CreatePost();
			post4.setUser(3L);
			post4.setContent("c'est le post 4");

			CreatePost post5 = new CreatePost();
			post5.setUser(3L);
			post5.setContent("c'est le post 5");


			CreatePost post6 = new CreatePost();
			post6.setUser(4L);
			post6.setContent("c'est le post 6");

			CreatePost post7 = new CreatePost();
			post7.setUser(Long.parseLong("2"));
			post7.setContent("c'est le post 7");

			// Save the posts using IPostService
			postService.create(post1);
			postService.create(post2);
			postService.create(post3);
			postService.create(post4);
			postService.create(post5);
			postService.create(post6);
			postService.create(post7);


			// Print the created users
			System.out.println("posts created ");
		};
	}


}

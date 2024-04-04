package com.aniss.postservice.entities;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "app_post")
@Data
@Accessors(chain = true)
public class Post {
    @GeneratedValue @Id private Long id;
    @Column(name = "\"user\"")
    private Long user;
    private String content;
}

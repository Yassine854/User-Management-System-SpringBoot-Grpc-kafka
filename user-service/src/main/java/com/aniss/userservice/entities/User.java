package com.aniss.userservice.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "app_user")
@Data
@Accessors(chain = true)
public class User {
    @GeneratedValue @Id private Long id;
    private String name;
    @CreationTimestamp private LocalDateTime createdAt;


}

package com.aniss.postservice.models;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Table;

@Data
@Accessors(chain = true)
public class User {
    private Long id;
    private String name;
}

package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="Roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(length = 60)
    private String name;

}

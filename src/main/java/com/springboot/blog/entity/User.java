package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name="Users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String username;
    private String email;
    private String password;
    @CreatedDate
    private Date createDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="user_roles",
        joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"))
    private Set<Role> roles;

}

package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;

@Entity
@Data
@Table(name="Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CommentId")
    private Long id;
    @Column(name="Name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="MessageBody")
    private String commentBody;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id", nullable = false)
    private Post post;
}

package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@Table(name="Comment")
@EntityListeners(AuditingEntityListener.class)
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
    @Column(name="DataTime", nullable = false)
    @CreatedDate
    private Date Date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id", nullable = false)
    private Post post;
}

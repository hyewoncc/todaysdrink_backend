package com.todaysdrink.todaysdrink.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "COMMENT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BEER_ID")
    private Beer beer;

    private String name;
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LIKECOMMENT_ID")
    private LikeComment like;

    @CreationTimestamp
    private Timestamp createTime;

    protected Comment(){}

    /* set */
    private void initComment(Beer beer, String name, String content) {
        this.beer = beer;
        this.name = name;
        this.content = content;
    }

    /* 생성 */
    public Comment createComment(Beer beer, String name, String content) {
        Comment comment = new Comment();
        comment.initComment(beer, name, content);
        return comment;
    }
}

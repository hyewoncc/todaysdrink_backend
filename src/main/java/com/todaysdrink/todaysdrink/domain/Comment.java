package com.todaysdrink.todaysdrink.domain;

import com.todaysdrink.todaysdrink.dto.CommentDto;
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
    private void initComment(Beer beer, CommentDto commentDto) {
        this.beer = beer;
        this.name = commentDto.getName();
        this.content = commentDto.getContent();
    }

    /* 생성 */
    public static Comment createComment(Beer beer, CommentDto commentDto) {
        Comment comment = new Comment();
        comment.initComment(beer, commentDto);
        return comment;
    }
}

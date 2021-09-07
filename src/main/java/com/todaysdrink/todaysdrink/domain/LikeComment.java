package com.todaysdrink.todaysdrink.domain;

import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
public class LikeComment {

    @Id
    @GeneratedValue
    @Column(name = "LIKECOMMENT_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMENT_ID")
    private Comment comment;

    private Long count;

    protected LikeComment(){}

    /* 편의 메서드 */
    public void upCount(){
        this.count += 1L;
    }
}

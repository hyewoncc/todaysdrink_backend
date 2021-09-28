package com.todaysdrink.todaysdrink.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
public class LikeComment {

    @Id
    @GeneratedValue
    @Column(name = "LIKECOMMENT_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "like")
    private Comment comment;

    private Long count;

    protected LikeComment(){}

    /* 편의 메서드 */
    public void upCount(){
        this.count += 1L;
    }

    public void downCount() {
        this.count -= 1L;
    }

    /* set */
    private void initLikeComment() {
        this.count = 0L;
    }

    /* 생성 */
    public static LikeComment createLikeComment() {
        LikeComment likeComment = new LikeComment();
        likeComment.initLikeComment();
        return likeComment;
    }
}

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMENT_ID")
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
    private void initLikeComment(Comment comment) {
        this.comment = comment;
        this.count = 0L;
    }

    /* 생성 */
    public LikeComment createLikeComment(Comment comment) {
        LikeComment likeComment = new LikeComment();
        likeComment.initLikeComment(comment);
        return likeComment;
    }
}

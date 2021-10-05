package com.todaysdrink.todaysdrink.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
public class LikeBeer {

    @Id
    @GeneratedValue
    @Column(name = "LIKEBEER_ID")
    private Long id;

    @OneToOne(mappedBy = "like")
    private Beer beer;

    private Long count;

    protected LikeBeer(){}

    /* 편의 메서드 */
    public void upCount() {
        this.count += 1L;
    }

    public void downCount() {
        this.count -= 1L;
    }

    /* set */
    private void initLikeBeer() {
        this.count = 0L;
    }

    /* 생성 */
    public static LikeBeer createLikeBeer() {
        LikeBeer likeBeer = new LikeBeer();
        likeBeer.initLikeBeer();
        return likeBeer;
    }
}

package com.todaysdrink.todaysdrink.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
public class LikeBeer {

    @Id
    @GeneratedValue
    @Column(name = "LIKEBEER_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BEER_ID")
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
    private void initLikeBeer(Beer beer) {
        this.beer = beer;
        this.count = 0L;
    }

    /* 생성 */
    public LikeBeer createLikeBeer(Beer beer) {
        LikeBeer likeBeer = new LikeBeer();
        likeBeer.initLikeBeer(beer);
        return likeBeer;
    }
}

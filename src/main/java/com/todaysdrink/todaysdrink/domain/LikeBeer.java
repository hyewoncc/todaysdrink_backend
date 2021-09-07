package com.todaysdrink.todaysdrink.domain;

import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
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
}

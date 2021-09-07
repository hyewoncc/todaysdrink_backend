package com.todaysdrink.todaysdrink.domain;

import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
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

    protected Comment(){}
}

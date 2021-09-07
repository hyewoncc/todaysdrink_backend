package com.todaysdrink.todaysdrink.domain;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
public class Beer {

    @Id
    @GeneratedValue
    @Column(name = "BEER_ID")
    private Long id;

    private String name;
    @Enumerated(EnumType.STRING)
    private Country country;
    private double alcohol;

    private int bitter;

    @Column(length = 512)
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LIKEBEER_ID")
    private LikeBeer like;

    @OneToMany(mappedBy = "beer", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    protected Beer(){};
}

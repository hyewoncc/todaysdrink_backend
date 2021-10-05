package com.todaysdrink.todaysdrink.dto;

import com.todaysdrink.todaysdrink.domain.LikeBeer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeBeerDto {
    private Long id;
    private Long beerId;
    private Long count;

    public LikeBeerDto(LikeBeer likeBeer) {
        id = likeBeer.getId();
        beerId = likeBeer.getBeer().getId();
        count = likeBeer.getCount();
    }
}

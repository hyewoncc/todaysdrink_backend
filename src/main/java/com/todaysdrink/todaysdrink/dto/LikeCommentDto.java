package com.todaysdrink.todaysdrink.dto;

import com.todaysdrink.todaysdrink.domain.LikeComment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeCommentDto {
    private Long id;
    private Long commentId;
    private Long count;

    public LikeCommentDto(LikeComment likeComment) {
        id = likeComment.getId();;
        commentId = likeComment.getComment().getId();
        count = likeComment.getCount();
    }
}

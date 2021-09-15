package com.todaysdrink.todaysdrink.dto;

import com.todaysdrink.todaysdrink.domain.Comment;
import com.todaysdrink.todaysdrink.domain.LikeComment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private Long beer_id;
    private String name;
    private String content;
    private Long like;

    public CommentDto(Comment comment) {
        id = comment.getId();
        beer_id = comment.getBeer().getId();
        name = comment.getName();
        content = comment.getContent();
        like = comment.getLike().getCount();
    }
}

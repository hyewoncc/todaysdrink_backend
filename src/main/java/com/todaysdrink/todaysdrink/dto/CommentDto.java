package com.todaysdrink.todaysdrink.dto;

import com.todaysdrink.todaysdrink.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private Long beerId;
    private String name;
    private String content;
    private Long commentLikeId;
    private Long commentLikes;

    public CommentDto(Comment comment) {
        id = comment.getId();
        beerId = comment.getBeer().getId();
        name = comment.getName();
        content = comment.getContent();
        commentLikeId = comment.getLike().getId();
        commentLikes = comment.getLike().getCount();
    }
}

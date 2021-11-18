package com.todaysdrink.todaysdrink.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.todaysdrink.todaysdrink.domain.LikeComment;
import com.todaysdrink.todaysdrink.dto.LikeCommentDto;
import com.todaysdrink.todaysdrink.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likecomments")
public class LikeCommentController {

    private final CommentService commentService;


    // 단일 댓글 좋아요 조회
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<LikeCommentDto>> findOne(@PathVariable Long id) {
        Optional<LikeComment> likeComment = commentService.getLikeComment(id);
        return likeComment.map(l -> EntityModel.of(new LikeCommentDto(l),
                linkTo(methodOn(LikeCommentController.class).findOne(likeComment.get().getId())).withSelfRel()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // 단일 댓글 좋아요 수정
    @PostMapping("/{id}")
    public ResponseEntity<?> modifyOne(@PathVariable Long id, @RequestParam String modify) {
        LikeComment likeComment = modify.equals("like") ? commentService.upLikeComment(id) : commentService.downLikeComment(id);
        return ResponseEntity.ok(new LikeCommentDto(likeComment));
    }
}

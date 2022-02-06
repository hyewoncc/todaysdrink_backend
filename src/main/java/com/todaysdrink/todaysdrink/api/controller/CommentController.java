package com.todaysdrink.todaysdrink.api.controller;

import com.todaysdrink.todaysdrink.domain.Beer;
import com.todaysdrink.todaysdrink.domain.Comment;
import com.todaysdrink.todaysdrink.dto.CommentDto;
import com.todaysdrink.todaysdrink.service.BeerService;
import com.todaysdrink.todaysdrink.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;
    private final BeerService beerService;


    // 특정 맥주의 모든 코멘트 조회
    @GetMapping("")
    public ResponseEntity<CollectionModel<EntityModel<CommentDto>>> findAll(@RequestParam("beerId")Long beerId , Pageable pageable) {
        List<Comment> comments = commentService.getCommentList(beerId);
        List<EntityModel<CommentDto>> commentDtos = comments.stream()
                .map(c -> EntityModel.of(new CommentDto(c),
                        linkTo(methodOn(LikeCommentController.class).findOne(c.getLike().getId())).withRel("like"),
                        linkTo(methodOn(LikeCommentController.class).modifyOne(c.getLike().getId(), "like")).withRel("uplike"),
                        linkTo(methodOn(LikeCommentController.class).modifyOne(c.getLike().getId(), "dislike")).withRel("dislike")))
                .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(commentDtos,
                linkTo(methodOn(CommentController.class).findAll(beerId, pageable)).withSelfRel()));
    }


    @PostMapping("")
    public ResponseEntity<?> saveOne(@RequestBody CommentDto commentDto) {
        Comment comment = commentService.saveComment(commentDto);
        Link newLink = linkTo(methodOn(BeerController.class).findOne(comment.getBeer().getId())).withSelfRel();
        try {
            return ResponseEntity.noContent().location(new URI(newLink.getHref())).build();
        }catch (URISyntaxException e) {
            return ResponseEntity.badRequest().body("Unable to save");
        }
    }
}

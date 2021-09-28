package com.todaysdrink.todaysdrink.api.controller;

import com.todaysdrink.todaysdrink.domain.Comment;
import com.todaysdrink.todaysdrink.dto.CommentDto;
import com.todaysdrink.todaysdrink.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

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

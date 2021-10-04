package com.todaysdrink.todaysdrink.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.todaysdrink.todaysdrink.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likebeers")
public class LikeBeerController {

    private final BeerService beerService;


    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Long>> findOne(@PathVariable Long id) {
        Long likes = beerService.getOneLikeBeer(id).get().getCount();
        EntityModel<Long> likesDto = EntityModel.of(likes,
                linkTo(methodOn(LikeBeerController.class).findOne(id)).withSelfRel());
        return ResponseEntity.ok(likesDto);
    }


    @PostMapping("/{id}")
    public ResponseEntity<?> modifyOne(@PathVariable Long id, @RequestParam String modify) {
        Long likes = 0L;
        if(modify.equals("up")) {
            likes = beerService.upLikeBeer(id);
        }
        if(modify.equals("down")) {
            likes = beerService.downLikeBeer(id);
        }
        EntityModel<Long> likesDto = EntityModel.of(likes,
                linkTo(methodOn(LikeBeerController.class).findOne(id)).withSelfRel());

        return ResponseEntity.ok(likesDto);
    }
}

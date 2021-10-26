package com.todaysdrink.todaysdrink.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.todaysdrink.todaysdrink.domain.LikeBeer;
import com.todaysdrink.todaysdrink.dto.LikeBeerDto;
import com.todaysdrink.todaysdrink.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likebeers")
public class LikeBeerController {

    private final BeerService beerService;


    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<LikeBeerDto>> findOne(@PathVariable Long id) {
        Optional<LikeBeer> likeBeer = beerService.getOneLikeBeer(id);
        return likeBeer.map(l -> EntityModel.of(new LikeBeerDto(l),
                            linkTo(methodOn(LikeBeerController.class).findOne(likeBeer.get().getId())).withSelfRel(),
                            linkTo(methodOn(LikeBeerController.class).findOne(likeBeer.get().getId())).withRel("query-event"),
                            linkTo(methodOn(BeerController.class).findOne(likeBeer.get().getBeer().getId())).withRel("beer")))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/{id}")
    public ResponseEntity<?> modifyOne(@PathVariable Long id, @RequestParam String modify) {
        LikeBeer likeBeer = modify.equals("like") ? beerService.upLikeBeer(id) : beerService.downLikeBeer(id);
        return ResponseEntity.ok(new LikeBeerDto(likeBeer));
    }
}

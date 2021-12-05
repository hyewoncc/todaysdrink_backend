package com.todaysdrink.todaysdrink.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.todaysdrink.todaysdrink.domain.Beer;
import com.todaysdrink.todaysdrink.dto.BeerDto;
import com.todaysdrink.todaysdrink.service.BeerService;
import com.todaysdrink.todaysdrink.service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommend")
public class RecommendController {
    
    private final BeerService beerService;
    private final RecommendService recommendService;

    @GetMapping("/beer/{id}")
    public ResponseEntity<CollectionModel<EntityModel<BeerDto>>> recommendByType(@PathVariable Long id) {
        Optional<Beer> beer = beerService.getOneBeer(id);
        List<Beer> recommendBeers = recommendService.getRecommendByType(beer.get());
        List<EntityModel<BeerDto>> beerDtos = recommendBeers.stream()
                .map(b -> EntityModel.of(new BeerDto(b),
                        linkTo(methodOn(BeerController.class).findOne(b.getId())).withSelfRel(),
                        linkTo(methodOn(LikeBeerController.class).findOne(b.getLike().getId())).withRel("like"),
                        linkTo(methodOn(LikeBeerController.class).modifyOne(b.getLike().getId(),"like")).withRel("uplike"),
                        linkTo(methodOn(LikeBeerController.class).modifyOne(b.getLike().getId(),"dislike")).withRel("dislike")))
                .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(beerDtos));
    }
}

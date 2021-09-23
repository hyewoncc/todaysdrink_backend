package com.todaysdrink.todaysdrink.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.todaysdrink.todaysdrink.domain.Beer;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;

import com.todaysdrink.todaysdrink.dto.BeerDto;
import com.todaysdrink.todaysdrink.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/beers")
public class BeerController {

    private final BeerService beerService;

    @GetMapping("")
    public ResponseEntity<CollectionModel<EntityModel<BeerDto>>> findAll(Pageable pageable) {
        Page<Beer> beers = beerService.getBeerList(pageable);
        List<EntityModel<BeerDto>> beerDtos = beers.stream()
                .map(b -> EntityModel.of(new BeerDto(b),
                        linkTo(methodOn(BeerController.class).findOne(b.getId())).withSelfRel()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(beerDtos,
                linkTo(methodOn(BeerController.class).findAll(pageable)).withSelfRel()));
    }


    @PostMapping("")
    public ResponseEntity<?> saveOne(@RequestBody BeerDto beerDto) {
        Beer beer = beerService.saveBeer(beerDto);
        Link newLink = linkTo(methodOn(BeerController.class).findOne(beer.getId())).withSelfRel();
        try {
            return ResponseEntity.noContent().location(new URI(newLink.getHref())).build();
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().body("Unable to save");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<BeerDto>> findOne(@PathVariable Long id) {
        Optional<Beer> beer = beerService.getOneBeer(id);
        return beer.map(b -> EntityModel.of(new BeerDto(b),
                        linkTo(methodOn(BeerController.class).findOne(b.getId())).withSelfRel()))
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
}

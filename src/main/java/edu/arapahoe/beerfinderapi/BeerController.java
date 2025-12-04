package edu.arapahoe.beerfinderapi;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BeerController {
    private final BeerRepository beerRepository;
    private final BreweryRepository breweryRepository;

    public BeerController(BeerRepository beerRepository, BreweryRepository breweryRepository) {
        this.beerRepository = beerRepository;
        this.breweryRepository = breweryRepository;
    }
    @GetMapping("/beers")
    List<Beer> getBeers() {
        return beerRepository.findAllByOrderByBeerId();
    }
    @GetMapping("/beers/{id}")
    ResponseEntity<Beer> getBeer(@PathVariable Integer id) {
        var beer = beerRepository.findById(id)
                .orElseThrow(()-> new BeerNotFoundException("Beer not found"));
        return ResponseEntity.ok(beer);
    }

    @PostMapping("/brewerySignIn")
    public String signIn(@RequestParam String username, @RequestParam String password) {
        List<Brewery> breweryList = breweryRepository.findAllByOrderByBreweryId();
        for (Brewery brewery : breweryList) {
            if (username.equals(brewery.getUsername()) && password.equals(brewery.getPassword())) {
                return "redirect:/breweryPage";
            }
        }
        return "redirect:/beers";
    }


}

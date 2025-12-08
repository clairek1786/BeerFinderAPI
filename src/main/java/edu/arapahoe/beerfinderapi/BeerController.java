package edu.arapahoe.beerfinderapi;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    // When printing beers for regular users, this will redact the user and password
    public Beer redactBeer(Beer beer) {
        Beer tempBeer = beer;
        tempBeer.getBrewery().setPassword("*****");
        tempBeer.getBrewery().setUsername("*****");
        return tempBeer;
    }

    @GetMapping("/beers")
    List<Beer> getBeers() {
        List<Beer> beerList = beerRepository.findAllByOrderByBeerId();
        List<Beer> redactedBeerList = new ArrayList<>();
        for (Beer beer : beerList) {
            redactedBeerList.add(redactBeer(beer));
        }
        return redactedBeerList;
    }
    @GetMapping("/beers/{id}")
    ResponseEntity<Beer> getBeer(@PathVariable Integer id) {
        var beer = beerRepository.findById(id)
                .orElseThrow(()-> new BeerNotFoundException("Beer not found"));
        return ResponseEntity.ok(redactBeer(beer));
    }

    /* This code demonstrates what a redirect looks like
    @GetMapping("/test")
    public void test(HttpServletResponse response) throws IOException {
        response.sendRedirect("/api/beers");
    }
     */

    @PostMapping("/brewerySignIn")
    public void signIn(HttpServletResponse response, @RequestParam String username, @RequestParam String password) throws IOException {
        List<Brewery> breweryList = breweryRepository.findAllByOrderByBreweryId();
        for (Brewery brewery : breweryList) {
            if (username.equals(brewery.getUsername()) && password.equals(brewery.getPassword())) {
                response.sendRedirect("/api/breweries/" + brewery.getBreweryId());
                return;
            }
        }
        response.sendRedirect("/api/beers");
    }

    @GetMapping("/breweries/{breweryId}")
    List<Beer> getBreweryBeers(@PathVariable Integer breweryId) {
        List<Beer> tempList = beerRepository.findAllByOrderByBeerId();
        List<Beer> beerList = new ArrayList<>();
        for (Beer beer : tempList) {
            if (beer.getBrewery().getBreweryId() == breweryId) {
                beerList.add(beer);
            }
        }
        return beerList;
    }

    @PostMapping("/breweries/{breweryId}")
    public ResponseEntity<Void> createBeer(@PathVariable Integer breweryId,
                                        @RequestParam Integer beerId,
                                        @RequestParam String name,
                                        @RequestParam String style,
                                        @RequestParam Float abv) {

        if (beerRepository.findById(beerId).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        var Beer = new Beer();
        Beer.setBeerId(beerId);
        Beer.setName(name);
        Beer.setStyle(style);
        Beer.setAbv(abv);
        Beer.setBrewery(breweryRepository.findById(breweryId).get());
        beerRepository.save(Beer);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/breweries/{breweryId}")
    ResponseEntity<Void> updateBeer(@PathVariable Integer breweryId,
                                    @RequestParam Integer beerId,
                                    @RequestParam String name,
                                    @RequestParam String style,
                                    @RequestParam Float abv) {
        var beer =  beerRepository.findById(beerId).orElseThrow(()-> new BeerNotFoundException("Beer not found"));
        beer.setName(name);
        beer.setStyle(style);
        beer.setAbv(abv);
        beer.setBrewery(breweryRepository.findById(breweryId).get());
        beerRepository.save(beer);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/breweries/{breweryId}")
    public ResponseEntity<Void> deleteBeer(@PathVariable Integer breweryId, @RequestParam Integer beerId) {
        var beer = beerRepository.findById(beerId).orElseThrow(()-> new BeerNotFoundException("Beer not found"));
        if (beer.getBrewery().getBreweryId() == breweryId) {
            beerRepository.delete(beer);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

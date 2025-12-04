package edu.arapahoe.beerfinderapi;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BreweryRepository extends JpaRepository<Brewery, Integer> {
    List<Brewery> findAllByOrderByBreweryId();
    Optional<BreweryInfo> findByBreweryId(Integer breweryId);
}
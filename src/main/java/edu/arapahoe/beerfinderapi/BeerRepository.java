package edu.arapahoe.beerfinderapi;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BeerRepository extends JpaRepository<Beer, Integer> {
    List<Beer> findAllByOrderByBeerId();
    Optional<BeerInfo> findByBeerId(Integer beerId);
}
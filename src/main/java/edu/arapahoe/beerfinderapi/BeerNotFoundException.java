package edu.arapahoe.beerfinderapi;

public class BeerNotFoundException extends RuntimeException {
    public BeerNotFoundException(String message) {
        super(message);
    }
}

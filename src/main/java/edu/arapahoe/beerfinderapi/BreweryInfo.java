package edu.arapahoe.beerfinderapi;

/**
 * Projection for {@link Brewery}
 */
public interface BreweryInfo {
    Integer getBreweryId();

    String getName();

    String getLocation();

    String getUsername();

    String getPassword();
}
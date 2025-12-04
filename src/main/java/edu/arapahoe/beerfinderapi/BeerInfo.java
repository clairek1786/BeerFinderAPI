package edu.arapahoe.beerfinderapi;

/**
 * Projection for {@link Beer}
 */
public interface BeerInfo {
    Integer getBeerId();

    String getName();

    String getStyle();

    Float getAbv();

    BreweryInfo getBrewery();
}
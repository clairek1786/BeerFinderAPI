package edu.arapahoe.beerfinderapi;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "\"Beers\"")
public class Beer {
    @Id
    @Column(name = "beer_id")
    private Integer beerId;

    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @Size(max = 50)
    @Column(name = "style", length = 50)
    private String style;

    @Column(name = "abv")
    private Float abv;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brewery_id", referencedColumnName = "brewery_id")
    private Brewery brewery;

    public Integer getBeerId() {
        return beerId;
    }

    public void setBeerId(Integer beerId) {
        this.beerId = beerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Float getAbv() {
        return abv;
    }

    public void setAbv(Float abv) {
        this.abv = abv;
    }

    public Brewery getBrewery() {
        return brewery;
    }

    public void setBrewery(Brewery brewery) {
        this.brewery = brewery;
    }

}
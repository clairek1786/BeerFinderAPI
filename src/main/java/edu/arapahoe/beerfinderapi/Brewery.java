package edu.arapahoe.beerfinderapi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "\"Breweries\"")
public class Brewery {
    @Id
    @Column(name = "brewery_id")
    private Integer breweryId;

    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @Size(max = 50)
    @Column(name = "\"Location\"", length = 50)
    private String location;

    @Size(max = 50)
    @Column(name = "username", length = 50)
    private String username;

    @Size(max = 50)
    @Column(name = "password", length = 50)
    private String password;

    public Integer getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(Integer breweryId) {
        this.breweryId = breweryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
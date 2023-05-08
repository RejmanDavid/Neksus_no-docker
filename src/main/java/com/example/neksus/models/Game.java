package com.example.neksus.models;

import java.time.LocalDate;

public class Game {

    private Long id;
    private String name;
    private LocalDate releaseDate;
    private String description;
    private String developer;

    private String imagePath;

    public Game() {
    }

    public Game(Long id, String name, LocalDate releaseDate, String description, String developer, String imagePath) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.description = description;
        this.developer = developer;
        this.imagePath = imagePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", description='" + description + '\'' +
                ", developer='" + developer + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}

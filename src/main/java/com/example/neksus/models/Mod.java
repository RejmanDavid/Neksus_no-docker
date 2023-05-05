package com.example.neksus.models;

import java.util.Date;

public class Mod {
    private Long id;
    private String name;
    private String description;
    private Long authorId;
    private Long gameId;

    private Date dateCreated;

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Long getGameId() {
        return gameId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }


    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
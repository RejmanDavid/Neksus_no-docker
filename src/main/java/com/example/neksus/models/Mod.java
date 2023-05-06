package com.example.neksus.models;

import java.util.Date;

public class Mod {
    private Long modId;
    private String modName;
    private String description;
    private String author;
    private Long gameId;
    private Date datePublished;
    private String thumbnailImageId;
    private Long trackCount;

    // Getters
    public Long getModId() {
        return modId;
    }

    public String getModName() {
        return modName;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public Long getGameId() {
        return gameId;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public String getThumbnailImageId() {
        return thumbnailImageId;
    }

    public Long getTrackCount() {
        return trackCount;
    }

    // Setters
    public void setModId(Long modId) {
        this.modId = modId;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public void setThumbnailImageId(String thumbnailImageId) {
        this.thumbnailImageId = thumbnailImageId;
    }

    public void setTrackCount(Long trackCount) {
        this.trackCount = trackCount;
    }
}

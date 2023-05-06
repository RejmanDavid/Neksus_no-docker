package com.example.neksus.models;

public class TrackedMod {
    private Long id;
    private Long modId;
    private Long userId;

    // Getters
    public Long getId() {
        return id;
    }

    public Long getModId() {
        return modId;
    }

    public Long getUserId() {
        return userId;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setModId(Long modId) {
        this.modId = modId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}

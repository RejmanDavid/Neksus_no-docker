package com.example.neksus.models;

public class ModDependency {
    private Long modDependencyId;
    private Long childModId;
    private Long parentModId;

    // Getters and setters
    public Long getModDependencyId() {
        return modDependencyId;
    }

    public void setModDependencyId(Long modDependencyId) {
        this.modDependencyId = modDependencyId;
    }

    public Long getChildModId() {
        return childModId;
    }

    public void setChildModId(Long childModId) {
        this.childModId = childModId;
    }

    public Long getParentModId() {
        return parentModId;
    }

    public void setParentModId(Long parentModId) {
        this.parentModId = parentModId;
    }
}

package com.example.neksus.models;

public class Changelogs {
    private Long changelogId;
    private String description;
    private String version;
    private Long modId;

    public Changelogs() {
    }

    public Changelogs(Long changelogId, String description, String version, Long modId) {
        this.changelogId = changelogId;
        this.description = description;
        this.version = version;
        this.modId = modId;
    }

    public Long getChangelogId() {
        return changelogId;
    }

    public void setChangelogId(Long changelogId) {
        this.changelogId = changelogId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getModId() {
        return modId;
    }

    public void setModId(Long modId) {
        this.modId = modId;
    }
}

package com.webkeens.kutumbapp;

public class Repository {

    private int rank;
    private String username,repositoryName,description,language,avatar;
    private int totalStars,forks;

    public Repository(int rank, String username, String repositoryName, String description, String language, String avatar, int totalStars, int forks) {
        this.rank = rank;
        this.username = username;
        this.repositoryName = repositoryName;
        this.description = description;
        this.language = language;
        this.avatar = avatar;
        this.totalStars = totalStars;
        this.forks = forks;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getTotalStars() {
        return totalStars;
    }

    public void setTotalStars(int totalStars) {
        this.totalStars = totalStars;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }
}

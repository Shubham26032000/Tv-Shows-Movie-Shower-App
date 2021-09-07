package com.example.tvshows.Model;

public class TvShow {
    private String title;
    private String vote;
    private String poster_path;
    private String dateOfRelease;

    public TvShow() {

    }

    public String getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(String dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public String getTitle() {
        return title;
    }

    public String getVote() {
        return vote;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setVote(String vote) {
        this.vote = vote;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}

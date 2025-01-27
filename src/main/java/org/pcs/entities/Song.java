package org.pcs.entities;

public class Song {
    private int id;
    private String title;
    private Artist artist;
    private Genre genre;
    private Movie movie;
    private int timesPlayed;

    public Song(){}

    public Song(String title, Artist artist, Genre genre, Movie movie, int timesPlayed){
        this.title = title;
        this.artist = artist;
        this.movie = movie;
        this.genre = genre;
        this.timesPlayed = timesPlayed;
    }

    public int getId() {
        return id;
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }

    public String getTitle() {
        return title;
    }

    public Artist getArtist() {
        return artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setTimesPlayed(int timesPlayed) {
        this.timesPlayed = timesPlayed;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist=" + artist +
                ", genre=" + genre +
                ", movie=" + movie +
                ", timesPlayed=" + timesPlayed +
                '}';
    }
}

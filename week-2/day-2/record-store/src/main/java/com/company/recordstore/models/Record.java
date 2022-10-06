package com.company.recordstore.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Record {

    @NotEmpty(message = "You must supply a value for artist.")
    private String artist;
    @NotEmpty(message = "You must supply a value for title.")
    private String title;

    @NotEmpty(message = "You must supply a value for year.")

    @Size(min = 4, max = 4, message = "Year must be exactly 4 digits.")
    private String year;

    private int id;

    public Record() {}

    public Record(String artist, String title, String year) {
        this.artist = artist;
        this.title = title;
        this.year = year;
    }

    public Record(String artist, String title, String year, int id) {
        this.artist = artist;
        this.title = title;
        this.year = year;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return id == record.id && Objects.equals(title, record.title) && Objects.equals(artist, record.artist) && Objects.equals(year, record.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, artist, year, id);
    }

    @Override
    public String toString() {
        return "Record{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", year='" + year + '\'' +
                ", id=" + id +
                '}';
    }
}

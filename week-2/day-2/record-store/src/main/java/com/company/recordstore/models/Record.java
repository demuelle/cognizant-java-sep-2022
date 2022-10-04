package com.company.recordstore.models;

import java.util.Objects;

public class Record {
    private String title;
    private String artist;
    private int id;

    public Record(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public Record(String title, String artist, int id) {
        this.title = title;
        this.artist = artist;
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
        return id == record.id && Objects.equals(title, record.title) && Objects.equals(artist, record.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, artist, id);
    }

    @Override
    public String toString() {
        return "Record{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", id=" + id +
                '}';
    }
}

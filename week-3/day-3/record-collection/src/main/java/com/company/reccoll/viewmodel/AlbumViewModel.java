package com.company.reccoll.viewmodel;

import com.company.reccoll.model.Artist;
import com.company.reccoll.model.Label;
import com.company.reccoll.model.Track;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AlbumViewModel {
    private int id;
    private Set<Track> tracks = new HashSet<>();
    private String title;
//    private int artistId;
    private Artist artist;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
//    private int labelId;
    private Label label;
    private BigDecimal listPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumViewModel that = (AlbumViewModel) o;
        return id == that.id && Objects.equals(tracks, that.tracks) && Objects.equals(title, that.title) && Objects.equals(artist, that.artist) && Objects.equals(releaseDate, that.releaseDate) && Objects.equals(label, that.label) && Objects.equals(listPrice, that.listPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tracks, title, artist, releaseDate, label, listPrice);
    }

    @Override
    public String toString() {
        return "AlbumViewModel{" +
                "id=" + id +
                ", tracks=" + tracks +
                ", title='" + title + '\'' +
                ", artist=" + artist +
                ", releaseDate=" + releaseDate +
                ", label=" + label +
                ", listPrice=" + listPrice +
                '}';
    }
}

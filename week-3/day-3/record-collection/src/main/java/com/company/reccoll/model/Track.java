package com.company.reccoll.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "track")
public class Track implements Serializable {

    @Id
    @Column(name = "track_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int albumId;
    private String title;
    private int runtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRunTime() {
        return runtime;
    }

    public void setRunTime(int runtime) {
        this.runtime = runtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return getId() == track.getId() &&
                getAlbumId() == track.getAlbumId() &&
                getRunTime() == track.getRunTime() &&
                Objects.equals(getTitle(), track.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAlbumId(), getTitle(), getRunTime());
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", albumId=" + albumId +
                ", title='" + title + '\'' +
                ", runTime=" + runtime +
                '}';
    }
}

package com.jrock.hsdemo;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

/**
 * @author berinle
 */

@Entity
@Table(name="MOVIE")
@Indexed
public class Movie {
    @DocumentId
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Field
    String title;
    @Field
    int runtime = 90;
    @Field
    String genre;

    //relationships


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}

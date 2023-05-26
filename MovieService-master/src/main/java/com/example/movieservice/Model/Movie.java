package com.example.movieservice.Model;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Moviecategory category;
    @NotNull
    private boolean isAvailable;

    public Movie(Long id, String name, Moviecategory category) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.isAvailable = false;
    }

    public Movie() {

    }

    public long getId() {
        return id;
    }



    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Moviecategory getCategory() {
        return category;
    }

    public void setCategory(Moviecategory category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

}

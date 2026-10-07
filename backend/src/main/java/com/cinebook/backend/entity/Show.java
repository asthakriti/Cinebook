package com.cinebook.backend.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;      // NEW: exact numbers for money
import java.time.LocalDateTime;   // NEW: date + time

@Entity
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // NEW: first link. Many shows → one movie (column movie_id)
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    // NEW: second link. Many shows → one screen (column screen_id)
    // Same as Seat → Screen, but now one entity has TWO links.
    @ManyToOne
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;

    private LocalDateTime startTime;   // NEW type: maps to start_time (DATETIME)
    private BigDecimal price;          // NEW type: maps to price (DECIMAL)

    public Show() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
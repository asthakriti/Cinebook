package com.cinebook.backend.repository;

import com.cinebook.backend.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;   // NEW: the method now takes a time
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Integer> {


    // SQL: SELECT * FROM shows
    //      WHERE movie_id = ? AND start_time > ?
    //      ORDER BY start_time ASC
    // Parameters are filled in the same order as in the name: movieId, then time
    List<Show> findByMovieIdAndStartTimeAfterOrderByStartTimeAsc(Integer movieId, LocalDateTime time);
}
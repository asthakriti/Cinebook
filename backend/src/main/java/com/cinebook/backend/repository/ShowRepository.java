package com.cinebook.backend.repository;

import com.cinebook.backend.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Integer> {

    // SAME pattern as seats: filter + sort in the method name
    // SQL: SELECT * FROM shows WHERE movie_id = ? ORDER BY start_time ASC
    List<Show> findByMovieIdOrderByStartTimeAsc(Integer movieId);
}
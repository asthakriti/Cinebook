package com.cinebook.backend.service;
import java.time.LocalDateTime;
import com.cinebook.backend.entity.Show;
import com.cinebook.backend.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final MovieService movieService;   // SAME idea as ScreenService using TheatreService

    public ShowService(ShowRepository showRepository, MovieService movieService) {
        this.showRepository = showRepository;
        this.movieService = movieService;
    }

    public List<Show> getShowsByMovie(Integer movieId) {
        // Reuse: throws MovieNotFoundException (404) if the movie doesn't exist
        movieService.getMovieById(movieId);

        return showRepository.findByMovieIdAndStartTimeAfterOrderByStartTimeAsc(
                movieId, LocalDateTime.now());    }
}

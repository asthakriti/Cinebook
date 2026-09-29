package com.cinebook.backend.service;

import com.cinebook.backend.entity.Movie;
import com.cinebook.backend.entity.MovieStatus;
import com.cinebook.backend.repository.MovieRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.cinebook.backend.exception.MovieNotFoundException;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Integer id) {
        Optional<Movie> result = movieRepository.findById(id);

        if (result.isEmpty()) {
            throw new MovieNotFoundException(id);
        }

        return result.get();
    }

    public Movie addMovie(Movie movie) {
        movie.setId(null);

        if (movie.getStatus() == null) {
            movie.setStatus(MovieStatus.COMING_SOON);
        }

        return movieRepository.save(movie);
    }

    public Movie updateMovie(Integer id, Movie updated) {
        Movie existing = getMovieById(id);

        existing.setTmdbId(updated.getTmdbId());
        existing.setTitle(updated.getTitle());
        existing.setGenre(updated.getGenre());
        existing.setLanguage(updated.getLanguage());
        existing.setDuration(updated.getDuration());
        existing.setPosterUrl(updated.getPosterUrl());

        if (updated.getStatus() != null) {
            existing.setStatus(updated.getStatus());
        }

        return movieRepository.save(existing);
    }

    public void deleteMovie(Integer id) {
        Movie existing = getMovieById(id);
        movieRepository.delete(existing);
    }
}
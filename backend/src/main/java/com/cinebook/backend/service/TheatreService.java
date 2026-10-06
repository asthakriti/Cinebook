package com.cinebook.backend.service;

import com.cinebook.backend.entity.Theatre;
import com.cinebook.backend.exception.ResourceNotFoundException;
import com.cinebook.backend.repository.TheatreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreService {

    private final TheatreRepository theatreRepository;

    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public List<Theatre> getTheatres(String city) {

        // NEW: city is null when the URL has no ?city=...
        // isBlank() also catches ?city= (empty) and ?city=   (spaces)
        if (city == null || city.isBlank()) {
            return theatreRepository.findAll();      // no filter → all theatres
        }

        return theatreRepository.findByCity(city);   // NEW: filter by city
    }

    public Theatre getTheatreById(Integer id) {
        return theatreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Theatre not found with id " + id));
    }
}
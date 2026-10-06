package com.cinebook.backend.service;

import com.cinebook.backend.entity.Screen;
import com.cinebook.backend.repository.ScreenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenService {

    private final ScreenRepository screenRepository;
    private final TheatreService theatreService;

    public ScreenService(ScreenRepository screenRepository, TheatreService theatreService) {
        this.screenRepository = screenRepository;
        this.theatreService = theatreService;
    }

    public List<Screen> getAllScreens() {
        return screenRepository.findAll();
    }

    public List<Screen> getScreensByTheatre(Integer theatreId) {
        theatreService.getTheatreById(theatreId);
        return screenRepository.findByTheatreId(theatreId);
    }
}
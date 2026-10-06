package com.cinebook.backend.controller;

import com.cinebook.backend.entity.Theatre;
import com.cinebook.backend.service.TheatreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TheatreController {

    private final TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @GetMapping("/theatres")
    public List<Theatre> getAllTheatres() {
        return theatreService.getAllTheatres();
    }

    @GetMapping("/theatres/{id}")
    public Theatre getTheatreById(@PathVariable Integer id) {
        return theatreService.getTheatreById(id);
    }
}

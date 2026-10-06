package com.cinebook.backend.controller;

import com.cinebook.backend.entity.Theatre;
import com.cinebook.backend.service.TheatreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;   // NEW

import java.util.List;

@RestController
public class TheatreController {

    private final TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @GetMapping("/theatres")
    public List<Theatre> getTheatres(@RequestParam(required = false) String city) {
        return theatreService.getTheatres(city);   // CHANGED: calls the new service method
    }

    @GetMapping("/theatres/{id}")
    public Theatre getTheatreById(@PathVariable Integer id) {
        return theatreService.getTheatreById(id);
    }
}

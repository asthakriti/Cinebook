package com.cinebook.backend.controller;

import com.cinebook.backend.entity.Show;
import com.cinebook.backend.service.ShowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    // SAME nested URL style as /theatres/{theatreId}/screens
    @GetMapping("/movies/{movieId}/shows")
    public List<Show> getShowsByMovie(@PathVariable Integer movieId) {
        return showService.getShowsByMovie(movieId);
    }
}

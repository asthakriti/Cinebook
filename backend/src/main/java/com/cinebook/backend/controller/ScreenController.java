package com.cinebook.backend.controller;

import com.cinebook.backend.entity.Screen;
import com.cinebook.backend.service.ScreenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@RestController
public class ScreenController {

    private final ScreenService screenService;

    public ScreenController(ScreenService screenService) {
        this.screenService = screenService;
    }

    @GetMapping("/screens")
    public List<Screen> getAllScreens() {
        return screenService.getAllScreens();
    }

    @GetMapping("/theatres/{theatreId}/screens")
    public List<Screen> getScreensByTheatre(@PathVariable Integer theatreId) {
        return screenService.getScreensByTheatre(theatreId);
    }
}
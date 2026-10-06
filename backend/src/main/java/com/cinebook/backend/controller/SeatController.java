package com.cinebook.backend.controller;

import com.cinebook.backend.entity.Seat;
import com.cinebook.backend.service.SeatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/screens/{screenId}/seats")
    public List<Seat> getSeatsByScreen(@PathVariable Integer screenId) {
        return seatService.getSeatsByScreen(screenId);
    }
}

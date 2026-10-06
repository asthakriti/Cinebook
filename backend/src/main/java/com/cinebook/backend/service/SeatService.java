package com.cinebook.backend.service;

import com.cinebook.backend.entity.Screen;
import com.cinebook.backend.entity.Seat;
import com.cinebook.backend.exception.ResourceNotFoundException;
import com.cinebook.backend.repository.ScreenRepository;
import com.cinebook.backend.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final ScreenRepository screenRepository;

    public SeatService(SeatRepository seatRepository, ScreenRepository screenRepository) {
        this.seatRepository = seatRepository;
        this.screenRepository = screenRepository;
    }

    public List<Seat> getSeatsByScreen(Integer screenId) {
        if (!screenRepository.existsById(screenId)) {
            throw new ResourceNotFoundException("Screen not found with id " + screenId);
        }
        return seatRepository.findByScreenIdOrderBySeatRowAscSeatNumberAsc(screenId);
    }
}
package com.cinebook.backend.repository;

import com.cinebook.backend.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

    List<Seat> findByScreenIdOrderBySeatRowAscSeatNumberAsc(Integer screenId);
}
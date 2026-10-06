package com.cinebook.backend.repository;

import com.cinebook.backend.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreenRepository extends JpaRepository<Screen, Integer> {

    List<Screen> findByTheatreId(Integer theatreId);
}
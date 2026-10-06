package com.cinebook.backend.repository;
import java.util.List;
import com.cinebook.backend.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Theatre, Integer> {
    List<Theatre> findByCity(String city);

}

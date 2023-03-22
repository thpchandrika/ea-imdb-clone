package edu.miu.repository;

import edu.miu.model.TVSeries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TVSeriesRepository extends JpaRepository<TVSeries, Long> {
    List<TVSeries> findAllByReleaseYear(String year);
    List<TVSeries> findAllByGenre(String genre);
}

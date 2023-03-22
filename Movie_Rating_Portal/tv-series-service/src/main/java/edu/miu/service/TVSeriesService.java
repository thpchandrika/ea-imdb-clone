package edu.miu.service;

import edu.miu.dto.TVSeriesDto;
import edu.miu.model.TVSeries;

import java.util.List;
import java.util.Optional;

public interface TVSeriesService {
    List<TVSeriesDto> getAll();
    Optional<TVSeriesDto> getById(Long id);
    TVSeriesDto add(TVSeriesDto tvSeries);
    TVSeriesDto update(long id, TVSeriesDto tvSeries);
    void deleteById(long id);

    List<TVSeriesDto> findByGenre(String keyword);
    List<TVSeriesDto> findByReleaseYear(String keyword);
}

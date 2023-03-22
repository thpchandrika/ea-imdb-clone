package edu.miu.service;

import edu.miu.dto.MovieDto;
import edu.miu.entity.Movie;

import java.util.List;

public interface MovieService {
    MovieDto getById(Long id);

    List<MovieDto> getAllMovies();
    MovieDto saveMovie(MovieDto movieDto);

    MovieDto updateMovie(MovieDto movieDto);

    void deleteMovie(Long id);

    List<MovieDto> getAllMoviesByReleaseYear(String year);
    List<MovieDto> getAllMoviesByRuntime(String runtime);
    List<MovieDto> getAllMoviesByGenre(String genre);
}

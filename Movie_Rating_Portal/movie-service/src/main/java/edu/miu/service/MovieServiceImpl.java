package edu.miu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.dto.MovieDto;
import edu.miu.entity.Movie;
import edu.miu.mapper.MovieMapper;
import edu.miu.repository.MovieRepository;
import edu.miu.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository repo;

    @Autowired
    private MovieMapper mapper;

    @Value("${movie.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    ObjectMapper om = new ObjectMapper();

    @Override
    @Cacheable(value = "moviesId", key = "{#id}")
    public MovieDto getById(Long id) {
        return mapper.toMovieDto(repo.getById(id));
    }

    @Override
    @Cacheable(value = "movies", key = "")
    public List<MovieDto> getAllMovies() {
        return mapper.toMovieDtos(repo.findAll());
    }

    @Override
    @Transactional
    @CacheEvict(value = "movie", allEntries = true)
    public MovieDto saveMovie(MovieDto movieDto) {
        Movie movie = mapper.dtoToMovie(movieDto);

        String message = null;
        try {
            message = om.writeValueAsString(movie);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(topicName, message);

        return mapper.toMovieDto(repo.save(movie));
    }

    @Override
    @CachePut(value = "updateMovie", key = "", unless = "")
    public MovieDto updateMovie(MovieDto movieDto) {
        Movie movie = mapper.dtoToMovie(movieDto);
        return mapper.toMovieDto(repo.save(movie));
    }

    @Override
    @CacheEvict(value = "room", key = "#id")
    public void deleteMovie(Long id) {
        repo.deleteById(id);
    }

    @Override
    @Cacheable(value = "moviesYear", key = "{#year}")
    public List<MovieDto> getAllMoviesByReleaseYear(String year) {
        return mapper.toMovieDtos(repo.findAllByReleaseYear(year));
    }

    @Override
    @Cacheable(value = "moviesRt", key = "{#runtime}")
    public List<MovieDto> getAllMoviesByRuntime(String runtime) {
        return mapper.toMovieDtos(repo.findAllByRunTime(runtime));
    }

    @Override
    @Cacheable(value = "moviesGenre", key = "{#genre}")
    public List<MovieDto> getAllMoviesByGenre(String genre) {
        return mapper.toMovieDtos(repo.findAllByGenre(genre));
    }
}

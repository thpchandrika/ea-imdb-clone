package edu.miu.controller;

import edu.miu.dto.MovieDto;
import edu.miu.service.MovieService;
//import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    @Autowired
    private MovieService service;

    private int attempts = 1;

    @GetMapping
    public ResponseEntity<List<MovieDto>> findAllMovies(){
        //return ResponseEntity.status(HttpStatus.OK).body(service.getAllMovies());
        return new ResponseEntity<>(service.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/year/{keyword}")
    @RolesAllowed("user")
    public ResponseEntity<List<MovieDto>> findMoviesByYear(@PathVariable String keyword){
        return new ResponseEntity<>(service.getAllMoviesByReleaseYear(keyword),HttpStatus.OK);
    }
    @GetMapping("/runTime/{keyword}")
    @RolesAllowed("user")
    public ResponseEntity<List<MovieDto>> findMoviesByRunTime(@PathVariable String keyword){
        return new ResponseEntity<>(service.getAllMoviesByRuntime(keyword),HttpStatus.OK);
    }
    @GetMapping("/genre/{keyword}")
    @RolesAllowed("user")
    public ResponseEntity<List<MovieDto>> findMoviesByGenre(@PathVariable String keyword){
        return new ResponseEntity<>(service.getAllMoviesByGenre(keyword),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @RolesAllowed("user")
    public ResponseEntity<MovieDto> findMovieById(@PathVariable Long id){
        return new ResponseEntity<>(service.getById(id),HttpStatus.OK);
    }

    @PostMapping()
    @RolesAllowed("admin")
//    @Retry(name = "movie-service", fallbackMethod = "fallback_retry")
    public ResponseEntity<MovieDto> saveMovie(@RequestBody MovieDto movieDto){
        return new ResponseEntity<>(service.saveMovie(movieDto),HttpStatus.OK);
    }

    @PutMapping()
    @RolesAllowed("admin")
    public ResponseEntity<MovieDto> updateMovie(@RequestBody MovieDto movieDto){
        return new ResponseEntity<>(service.updateMovie(movieDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("admin")
    public void deleteMovie(@PathVariable Long id){
        service.deleteMovie(id);
    }

    private ResponseEntity<String> fallback_retry(Exception e) {
        attempts = 1;
        return new ResponseEntity<String>("Movie service is down", HttpStatus.INTERNAL_SERVER_ERROR);

    }
}

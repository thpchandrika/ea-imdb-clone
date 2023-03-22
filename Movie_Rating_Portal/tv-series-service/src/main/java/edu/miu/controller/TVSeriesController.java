package edu.miu.controller;

import edu.miu.dto.TVSeriesDto;
import edu.miu.model.TVSeries;
import edu.miu.service.TVSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tv-series")
public class TVSeriesController {
    @Autowired
    private TVSeriesService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        System.out.println(id);
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody TVSeriesDto tvSeries) {
        return new ResponseEntity<>(service.add(tvSeries), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable long id, @RequestBody TVSeriesDto tvSeries) {
        return new ResponseEntity<>(service.update(id, tvSeries), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) {
        service.deleteById(id);
        return new ResponseEntity<>("TV Series Deleted", HttpStatus.OK);
    }

    @GetMapping("/year/{keyword}")
    public ResponseEntity<List<TVSeriesDto>> findByReleaseYear(@PathVariable String keyword){
        return new ResponseEntity<>(service.findByReleaseYear(keyword),HttpStatus.OK);
    }

    @GetMapping("/genre/{keyword}")
    public ResponseEntity<List<TVSeriesDto>> findByGenre(@PathVariable String keyword){
        return new ResponseEntity<>(service.findByGenre(keyword),HttpStatus.OK);
    }


}

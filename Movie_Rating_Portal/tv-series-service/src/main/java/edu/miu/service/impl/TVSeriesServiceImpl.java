package edu.miu.service.impl;

import edu.miu.dto.TVSeriesDto;
import edu.miu.model.TVSeries;
import edu.miu.repository.TVSeriesRepository;
import edu.miu.service.TVSeriesService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TVSeriesServiceImpl implements TVSeriesService {
    @Autowired
    private TVSeriesRepository repository;

    private final ModelMapper mapper;

    public List<TVSeriesDto> getAll(){
        return repository.findAll().stream().map(u -> mapper.map(u, TVSeriesDto.class)).collect(Collectors.toList());
    }

    public Optional<TVSeriesDto> getById(Long id){
        TVSeries tvSeries = repository.findById(id).get();
        Optional<TVSeriesDto> tvSeriesDto = Optional.of(mapper.map(tvSeries, TVSeriesDto.class));
       return tvSeriesDto;
    }

    public TVSeriesDto add(TVSeriesDto tvSeriesDto){
        TVSeries tvSeries1 = mapper.map(tvSeriesDto, TVSeries.class);
        tvSeries1 = repository.save(tvSeries1);
        return mapper.map(tvSeries1, TVSeriesDto.class);
    }

    public TVSeriesDto update(long id, TVSeriesDto tvSeriesDto){
        TVSeries tvSeries1 = repository.getById(id);
        TVSeries tvSeries = mapper.map(tvSeriesDto, TVSeries.class);
        tvSeries1.setActors(tvSeries.getActors());
        tvSeries1.setTitle(tvSeries.getTitle());
        tvSeries1.setDescription(tvSeries.getDescription());
        tvSeries1.setGenre(tvSeries.getGenre());
        tvSeries1.setSeasons(tvSeries.getSeasons());
        tvSeries1.setReleaseYear(tvSeries.getReleaseYear());
        tvSeries1.setSynopsis(tvSeries.getSynopsis());
        return mapper.map(repository.save(tvSeries1), TVSeriesDto.class);
    }

    public void deleteById(long id){
        repository.deleteById(id);
    }

    @Override
    public List<TVSeriesDto> findByGenre(String keyword) {
        return repository.findAllByGenre(keyword).stream().map(u -> mapper.map(u, TVSeriesDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<TVSeriesDto> findByReleaseYear(String keyword) {
        return repository.findAllByReleaseYear(keyword).stream().map(u -> mapper.map(u, TVSeriesDto.class)).collect(Collectors.toList());
    }
}

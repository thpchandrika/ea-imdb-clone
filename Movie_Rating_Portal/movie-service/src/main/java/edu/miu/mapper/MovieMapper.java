package edu.miu.mapper;

import edu.miu.dto.MovieDto;
import edu.miu.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,uses = {ProductionCompanyMapper.class})
public interface MovieMapper {
    MovieDto toMovieDto(Movie movie);

    Movie dtoToMovie(MovieDto movieDto);

    List<MovieDto> toMovieDtos(List<Movie> movies);
}

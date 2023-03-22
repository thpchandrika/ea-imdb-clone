package edu.miu.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeriesDto {
    private Long id;
    private String title;
    private String description;
    private String genre;
    private String synopsis;
    private int releaseYear;
    private String director;
    private String actors;
    private String seasons;
}

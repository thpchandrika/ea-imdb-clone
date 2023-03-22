package edu.miu.dto;

import lombok.Data;

@Data
public class TVSeriesDto {
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

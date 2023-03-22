package edu.miu.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewDto {

    private Long id;
    private double rate;
    private int likeButton;
    private int dislikeButton;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private LocalDate deletedDate;
    private String statusName;
    private Long userId;
    private Long movieId;
    private Long tvSeriesId;
}

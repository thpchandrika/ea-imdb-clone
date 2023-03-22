package edu.miu.service;

import edu.miu.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getAllReviews();

    void saveReview(ReviewDto reviewDto);

    void updateReview(ReviewDto reviewDto);

    void deleteReview(ReviewDto reviewDto);

    List<ReviewDto> getReviewListByMovieId(Long movieId);

    List<ReviewDto> getReviewListByTvSeriesId(Long tvSeriesId);

    List<ReviewDto> getReviewListByUserId(Long userId);
}

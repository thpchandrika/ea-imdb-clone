package edu.miu.controller;

import edu.miu.aop.ExecutionTime;
import edu.miu.dto.ReviewDto;
import edu.miu.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
@Slf4j
public class ReviewController {

    private ReviewService reviewService;

    @GetMapping
    @ExecutionTime
    public List<ReviewDto> getAllReviews() {
        log.info("Inside get all Review function.");
        return reviewService.getAllReviews();
    }

    @PostMapping
    @ExecutionTime
    public void saveReview(@RequestBody ReviewDto reviewDto) {
        reviewService.saveReview(reviewDto);
    }

    @PutMapping
    @ExecutionTime
    public void updateReview(@RequestBody ReviewDto reviewDto) {
        reviewService.updateReview(reviewDto);
    }

    @DeleteMapping
    @ExecutionTime
    public void deleteReview(@RequestBody ReviewDto reviewDto) {
        reviewService.deleteReview(reviewDto);
    }

    @GetMapping("/movies/{movieId}")
    @ExecutionTime
    public List<ReviewDto> getReviewListByMovieId(@PathVariable Long movieId) {
        return reviewService.getReviewListByMovieId(movieId);
    }

    @GetMapping("/tvSeries/{tvSeriesId}")
    @ExecutionTime
    public List<ReviewDto> getReviewListByTvSeriesId(@PathVariable Long tvSeriesId) {
        return reviewService.getReviewListByTvSeriesId(tvSeriesId);
    }

    @GetMapping("/userId/{userId}")
    @ExecutionTime
    public List<ReviewDto> getReviewListByUserId(@PathVariable Long userId) {
        return reviewService.getReviewListByUserId(userId);
    }
}

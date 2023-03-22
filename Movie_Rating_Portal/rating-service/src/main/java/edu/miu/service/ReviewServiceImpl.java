package edu.miu.service;

import edu.miu.util.StatusConstant;
import edu.miu.dto.ReviewDto;
import edu.miu.entity.Review;
import edu.miu.mapper.ReviewMapper;
import edu.miu.repository.ReviewRepository;
import edu.miu.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private ReviewMapper reviewMapper;

    @Override
    public List<ReviewDto> getAllReviews() {
        return reviewMapper.entitiesToDtos(reviewRepository.findAllByStatusNameNotLike(StatusConstant.DELETED));
    }

    @Override
    public void saveReview(ReviewDto reviewDto) {
        Review review = reviewMapper.dtoToEntity(reviewDto);
        review.setCreatedDate(LocalDate.now());
        review.setStatusName(StatusConstant.CREATED);
        reviewRepository.save(review);
    }

    @Override
    public void updateReview(ReviewDto reviewDto) {
        Review review = reviewMapper.dtoToEntity(reviewDto);
        review.setUpdatedDate(LocalDate.now());
        review.setStatusName(StatusConstant.UPDATED);
        reviewRepository.save(review);
    }

    @Override
    public void deleteReview(ReviewDto reviewDto) {
        Review review = reviewMapper.dtoToEntity(reviewDto);
        review.setDeletedDate(LocalDate.now());
        review.setStatusName(StatusConstant.DELETED);
        reviewRepository.save(review);
    }

    @Override
    public List<ReviewDto> getReviewListByMovieId(Long movieId) {
        return reviewMapper.entitiesToDtos(reviewRepository.findAllByMovieId(movieId));
    }

    @Override
    public List<ReviewDto> getReviewListByTvSeriesId(Long tvSeriesId) {
        return reviewMapper.entitiesToDtos(reviewRepository.findAllByTvSeriesId(tvSeriesId));
    }

    @Override
    public List<ReviewDto> getReviewListByUserId(Long userId) {
        return reviewMapper.entitiesToDtos(reviewRepository.findAllByUserId(userId));
    }
}
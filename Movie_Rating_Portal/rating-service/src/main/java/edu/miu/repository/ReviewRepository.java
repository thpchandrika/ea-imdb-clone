package edu.miu.repository;

import edu.miu.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByStatusNameNotLike(String status);

    List<Review> findAllByMovieId(Long movieId);

    List<Review> findAllByTvSeriesId(Long tvSeriesId);

    List<Review> findAllByUserId(Long userId);

    @Modifying
    @Transactional
    @Query(value = "update review set is_user_deleted =?1 where user_id =?2", nativeQuery = true)
    int updateDeletedUser(boolean flag, Long userId);
}

package edu.miu.repository;

import edu.miu.entity.Movie;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    List<Movie> findAllByReleaseYear(String year);
//    List<Movie> findAllByRating(String rating);
    List<Movie> findAllByGenre(String genre);
//    List<Movie> findAllByReleaseYear(String director);
//    List<Movie> findAllByReleaseYear(String actor);
    List<Movie> findAllByRunTime(String runTime);

}

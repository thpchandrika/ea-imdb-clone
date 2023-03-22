package edu.miu.controller;

import edu.miu.dtos.CommentRequestDto;
import edu.miu.dtos.CommentResponseDto;
import edu.miu.dtos.MovieDto;
import edu.miu.dtos.UserDto;
import edu.miu.exception.CommentNotFoundException;
import edu.miu.service.CommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movie-comments")
public class CommentController {
    private final CommentService commentService;
    private final RestTemplate restTemplate;
    private final ModelMapper mapper;

    @GetMapping("/{id}")
    @ApiOperation(value = "Finds user comment by id",
            notes = "provide id to get comment",
            response = CommentResponseDto.class)
    public CommentResponseDto getComment(@PathVariable Long id) throws CommentNotFoundException {
        var commentDto = commentService.getById(id);
        return getUserAndMovieDetail(commentDto);
    }

    private CommentResponseDto getUserAndMovieDetail(CommentResponseDto commentDto) {
        CommentResponseDto displayTemplate = mapper.map(commentDto, CommentResponseDto.class);
//        MovieDto movieDto = restTemplate.getForObject("http://movie-service/movies/" + commentDto.getMovieId(),
//                MovieDto.class);
//        UserDto userDto = restTemplate.getForObject("http://user-service/users/" + commentDto.getUserId(),
//                UserDto.class);
        MovieDto movieDto = new MovieDto("movie title 1", "movie1 description",
                "action", "2020", "2 hour 30 min");
        UserDto userDto = new UserDto(1L, "user@gmail.com",
                "Address 1", "Address2", "City", "state",
                "country", "zip");
        displayTemplate.setMovie(movieDto);
        displayTemplate.setUser(userDto);
        return displayTemplate;
    }

    @PostMapping
    @ApiOperation(value = "Adds comments")
    public CommentRequestDto save(@RequestBody @Valid CommentRequestDto comment) {
        return commentService.save(comment);
    }

    @GetMapping
    @ApiOperation(value = "Find all comments")
    @RolesAllowed("admin")
    public List<CommentResponseDto> getAll() {
        List<CommentResponseDto> response = new ArrayList<>();
        var commentList = commentService.getAll();
        commentList.forEach(c -> response.add(getUserAndMovieDetail(c)));
        return response;
    }

    @GetMapping("/getAllByUserId/{userId}")
    @ApiOperation(value = "Find all comments by userId")
    @RolesAllowed("admin")
    public List<CommentResponseDto> getAllByUserId(@PathVariable Long userId)
            throws CommentNotFoundException {
        List<CommentResponseDto> response = new ArrayList<>();
        var commentList = commentService.getAllByUserId(userId);
        commentList.forEach(c -> response.add(getUserAndMovieDetail(c)));
        return response;
    }

    @GetMapping("/getAllByMovieId/{movieId}")
    @ApiOperation(value = "Find all comments by movieId")
    @RolesAllowed("admin")
    public List<CommentResponseDto> getAllByMovieId(@PathVariable Long movieId)
            throws CommentNotFoundException {
        List<CommentResponseDto> response = new ArrayList<>();
        var commentList = commentService.getAllByMovieId(movieId);
        commentList.forEach(c -> response.add(getUserAndMovieDetail(c)));
        return response;
    }

    @GetMapping("/getAllByMovieIdAndUserId")
    @ApiOperation(value = "Find all comments by movieId and userId")
    @RolesAllowed("admin")
    public List<CommentResponseDto> getAllByMovieIdAndUserId(@RequestParam Long movieId,
                                                             @RequestParam Long userId)
            throws CommentNotFoundException {
        List<CommentResponseDto> response = new ArrayList<>();
        var commentList = commentService.getAllByUserIdAndMovieId(userId, movieId);
        commentList.forEach(c -> response.add(getUserAndMovieDetail(c)));
        return response;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes the comment by id")
    public void delete(@PathVariable Long id) {
        commentService.delete(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update comment by id",
            notes = "provide id as path varaible and request body")
    public void update(@PathVariable("id") Long id, @RequestBody @Valid CommentRequestDto updatedComment)
            throws CommentNotFoundException {
        commentService.update(id, updatedComment);
    }
}

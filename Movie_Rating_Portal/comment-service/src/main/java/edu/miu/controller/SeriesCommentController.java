package edu.miu.controller;

import edu.miu.dtos.*;
import edu.miu.exception.CommentNotFoundException;
import edu.miu.service.CommentService;
import edu.miu.service.SeriesCommentService;
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
@RequestMapping("/api/series-comments")
public class SeriesCommentController {
    private final SeriesCommentService commentService;
    private final RestTemplate restTemplate;
    private final ModelMapper mapper;

    @GetMapping("/{id}")
    @ApiOperation(value = "Finds user comment by id",
            notes = "provide id to get comment",
            response = SeriesCommentResponseDto.class)
    public SeriesCommentResponseDto getComment(@PathVariable Long id) throws CommentNotFoundException {
        var commentDto = commentService.getById(id);
        return getUserAndMovieDetail(commentDto);
    }

    private SeriesCommentResponseDto getUserAndMovieDetail(SeriesCommentResponseDto commentDto) {
        SeriesCommentResponseDto displayTemplate = mapper.map(commentDto, SeriesCommentResponseDto.class);
//        SeriesDto seriesDto = restTemplate.getForObject("http://tv-series-service/tv-series/" + commentDto.getMovieId(),
//                MovieDto.class);
//        UserDto userDto = restTemplate.getForObject("http://user-service/users/" + commentDto.getUserId(),
//                UserDto.class);

        //static data for testing
        SeriesDto seriesDto = new SeriesDto(1L, "series 1", "series desc", "thriller", "synop", 2020, "director", "actor", "seasons");
        UserDto userDto = new UserDto(1L, "user@gmail.com", "Address 1", "Address2", "City", "state", "country", "zip");
        displayTemplate.setSeries(seriesDto);
        displayTemplate.setUser(userDto);
        return displayTemplate;
    }

    @PostMapping
    @ApiOperation(value = "Adds comments")
    public SeriesCommentRequestDto save(@RequestBody @Valid SeriesCommentRequestDto comment) {
        return commentService.save(comment);
    }

    @GetMapping
    @ApiOperation(value = "Find all comments")
    @RolesAllowed("admin")
    public List<SeriesCommentResponseDto> getAll() {
        List<SeriesCommentResponseDto> response = new ArrayList<>();
        var commentList = commentService.getAll();
        commentList.forEach(c -> response.add(getUserAndMovieDetail(c)));
        return response;
    }

    @GetMapping("/getAllByUserId/{userId}")
    @ApiOperation(value = "Find all comments by userId")
    @RolesAllowed("admin")
    public List<SeriesCommentResponseDto> getAllByUserId(@PathVariable Long userId)
            throws CommentNotFoundException {
        List<SeriesCommentResponseDto> response = new ArrayList<>();
        var commentList = commentService.getAllByUserId(userId);
        commentList.forEach(c -> response.add(getUserAndMovieDetail(c)));
        return response;
    }

    @GetMapping("/getAllBySeriesId/{seriesId}")
    @ApiOperation(value = "Find all comments by seriesId")
    @RolesAllowed("admin")
    public List<SeriesCommentResponseDto> getAllBySeriesId(@PathVariable Long seriesId)
            throws CommentNotFoundException {
        List<SeriesCommentResponseDto> response = new ArrayList<>();
        var commentList = commentService.getAllBySeriesId(seriesId);
        commentList.forEach(c -> response.add(getUserAndMovieDetail(c)));
        return response;
    }

    @GetMapping("/getAllBySeriesIdAndUserId")
    @ApiOperation(value = "Find all comments by userId and seriesId")
    @RolesAllowed("admin")
    public List<SeriesCommentResponseDto> getAllByUserIdAndSeriesId(@RequestParam Long userId, @RequestParam Long seriesId)
            throws CommentNotFoundException {
        List<SeriesCommentResponseDto> response = new ArrayList<>();
        var commentList = commentService.getAllBySeriesIdAndUserId(seriesId, userId);
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
    public void update(@PathVariable("id") Long id, @RequestBody @Valid SeriesCommentRequestDto updatedComment) throws CommentNotFoundException {
        commentService.update(id, updatedComment);
    }
}

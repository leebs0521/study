package kuke.board.comment.controller;

import kuke.board.comment.service.CommentService;
import kuke.board.comment.service.request.CommentCreateRequest;
import kuke.board.comment.service.response.CommentPageResponse;
import kuke.board.comment.service.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService service;

    @GetMapping("/v1/comments/{commentId}")
    public CommentResponse read(
            @PathVariable Long commentId
    ) {
        return service.read(commentId);
    }

    @GetMapping("/v1/comments")
    public CommentPageResponse readAll(
            @RequestParam Long articleId,
            @RequestParam Long page,
            @RequestParam Long pageSize
    ) {
        return service.readAll(articleId, page, pageSize);
    }

    @GetMapping("/v1/comments/infinite-scroll")
    public List<CommentResponse> readAll(
            @RequestParam Long articleId,
            @RequestParam(required = false) Long lastParentCommentId,
            @RequestParam(required = false) Long lastCommentId,
            @RequestParam Long pageSize
    ) {
        return service.readAll(articleId, lastParentCommentId, lastCommentId, pageSize);
    }

    @PostMapping("/v1/comments")
    public CommentResponse create(
            @RequestBody CommentCreateRequest request
    ) {
        return service.create(request);
    }

    @DeleteMapping("/v1/comments/{commentId}")
    public void delete(
            @PathVariable Long commentId
    ) {
        service.delete(commentId);
    }

}

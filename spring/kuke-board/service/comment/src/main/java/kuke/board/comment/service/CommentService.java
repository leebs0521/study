package kuke.board.comment.service;

import kuke.board.comment.entity.Comment;
import kuke.board.comment.repository.CommentRepository;
import kuke.board.comment.service.request.CommentCreateRequest;
import kuke.board.comment.service.response.CommentPageResponse;
import kuke.board.comment.service.response.CommentResponse;
import kuke.board.common.snowflake.Snowflake;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.function.Predicate.not;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {

    private final Snowflake snowflake = new Snowflake();
    private final CommentRepository repository;

    public CommentResponse create(CommentCreateRequest request) {
        Comment parent = findParent(request);
        Comment comment = repository.save(
                Comment.create(snowflake.nextId(), request.content(), parent == null ? null : parent.getCommentId(), request.articleId(), request.writerId())
        );
        return CommentResponse.from(comment);
    }

    public void delete(Long commentId) {
        repository.findById(commentId)
                .filter(not(Comment::getDeleted))
                .ifPresent(comment -> {
                    if (hasChildren(comment)) {
                        comment.delete();
                    } else {
                        delete(comment);
                    }
                });
    }

    @Transactional(readOnly = true)
    public CommentResponse read(Long commentId) {
        return CommentResponse.from(
                repository.findById(commentId).orElseThrow()
        );
    }

    @Transactional(readOnly = true)
    public CommentPageResponse readAll(Long articleId, Long page, Long pageSize) {
        return CommentPageResponse.of(
                repository.findAll(articleId, (page - 1) * pageSize, pageSize).stream()
                        .map(CommentResponse::from)
                        .toList(),
                repository.count(articleId, PageLimitCalculator.calculatePageLimit(page, pageSize, 10L))
        );
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> readAll(Long articleId, Long lastParentCommentId, Long lastCommentId, Long limit) {
        List<Comment> comments = lastParentCommentId == null || lastCommentId == null
                ? repository.findAllInfiniteScroll(articleId, limit)
                : repository.findAllInfiniteScroll(articleId, lastParentCommentId, lastCommentId, limit);

        return comments.stream()
                .map(CommentResponse::from)
                .toList();
    }

    private boolean hasChildren(Comment comment) {
        return repository.countBy(comment.getArticleId(), comment.getCommentId(), 2L) == 2;
    }

    private void delete(Comment comment) {
        repository.delete(comment);
        if (!comment.isRoot()) {
            repository.findById(comment.getParentCommentId())
                    .filter(Comment::getDeleted)
                    .filter(not(this::hasChildren))
                    .ifPresent(this::delete);
        }
    }

    private Comment findParent(CommentCreateRequest request) {
        Long parentCommentId = request.parentCommentId();
        if (parentCommentId == null) {
            return null;
        }

        return repository.findById(parentCommentId)
                .filter(not(Comment::getDeleted))
                .filter(Comment::isRoot)
                .orElseThrow();
    }
}

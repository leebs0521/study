package kuke.board.comment.repository;

import kuke.board.comment.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CommentRepository {

    private final CommentJpaRepository jpaRepository;
    private final CommentQueryRepository queryRepository;

    public Optional<Comment> findById(Long id) {
        return jpaRepository.findById(id);
    }

    public Comment save(Comment comment) {
        return jpaRepository.save(comment);
    }

    public Long countBy(Long articleId, Long parentCommentId, Long limit) {
        return queryRepository.countBy(articleId, parentCommentId, limit);
    }

    public List<Comment> findAll(Long articleId, Long offset, Long limit) {
        return queryRepository.findAll(articleId, offset, limit);
    }

    public Long count(Long articleId, Long limit) {
        return queryRepository.count(articleId, limit);
    }

    public List<Comment> findAllInfiniteScroll(Long articleId, Long limit) {
        return queryRepository.findAllInfiniteScroll(articleId, limit);
    }

    public List<Comment> findAllInfiniteScroll(Long articleId, Long lastParentCommentId, Long lastCommentId, Long limit) {
        return queryRepository.findAllInfiniteScroll(articleId, lastParentCommentId, lastCommentId, limit);
    }

    public void delete(Comment comment) {
        jpaRepository.delete(comment);
    }
}

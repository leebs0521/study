package kuke.board.comment.repository;

import kuke.board.comment.entity.CommentV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CommentRepositoryV2 {

    private final CommentJpaRepositoryV2 jpaRepository;
    private final CommentQueryRepositoryV2 queryRepository;

    public Optional<CommentV2> findByPath(String path) {
        return queryRepository.findByPath(path);
    }

    public CommentV2 save(CommentV2 comment) {
        return jpaRepository.save(comment);
    }

    public void delete(CommentV2 comment) {
        jpaRepository.delete(comment);
    }

    public Optional<CommentV2> findById(Long id) {
        return jpaRepository.findById(id);
    }

    public Optional<String> findDescendantsTopPath(
            Long articleId, String pathPrefix
    ) {
        return queryRepository.findDescendantsTopPath(articleId, pathPrefix);
    }

    public List<CommentV2> findAll(Long articleId, Long offset, Long limit) {
        return queryRepository.findAll(articleId, offset, limit);
    }

    public Long count(Long articleId, Long limit) {
        return queryRepository.count(articleId, limit);
    }

    public List<CommentV2> findAllInfiniteScroll(Long articleId, Long limit) {
        return queryRepository.findAllInfiniteScroll(articleId, limit);
    }

    public List<CommentV2> findAllInfiniteScroll(Long articleId, String lastPath, Long limit) {
        return queryRepository.findAllInfiniteScroll(articleId, lastPath, limit);
    }

}

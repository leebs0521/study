package kuke.board.article.repository;

import kuke.board.article.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class ArticleRepository {

    private final ArticleJpaRepository jpaRepository;
    private final ArticleQueryRepository queryRepository;

    public Article save(Article article) {
        return jpaRepository.save(article);
    }

    public Optional<Article> findById(Long articleId) {
        return jpaRepository.findById(articleId);
    }

    public void deleteById(Long articleId) {
        jpaRepository.deleteById(articleId);
    }

    public List<Article> findAll(Long boardId, long offset, Long pageSize) {
        return queryRepository.findAll(boardId, offset, pageSize);
    }

    public Long count(Long boardId, Long limit) {
        return queryRepository.count(boardId, limit);
    }

    public List<Article> findAllInfiniteScroll(Long boardId, Long limit, Long lastArticleId) {
        return queryRepository.findAllInfiniteScroll(boardId, limit, lastArticleId);
    }

    public List<Article> findAllInfiniteScroll(Long boardId, Long limit) {
        return queryRepository.findAllInfiniteScroll(boardId, limit);
    }

    public void delete(Article article) {
        jpaRepository.delete(article);
    }
}

package kuke.board.article.repository;

import kuke.board.article.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class ArticleQueryRepositoryTest {

    @Autowired
    ArticleQueryRepository articleQueryRepository;

    @Test
    void findAllTest() {
        List<Article> articles = articleQueryRepository.findAll(1L, 1499970L, 30L);
        log.info("articles.size = {}", articles.size());
        for (Article article : articles) {
            log.info("article = {}", article);
        }
    }

    @Test
    void countTest() {
        Long count = articleQueryRepository.count(1L, 10000L);
        log.info("count: {}", count);
    }

    @Test
    void findAllInfiniteScrollTest() {
        List<Article> articles = articleQueryRepository.findAllInfiniteScroll(1L, 30L);
        log.info("articles.size = {}", articles.size());
        for (Article article : articles) {
            log.info("article = {}", article);
        }

        Long lastArticleId = articles.getLast().getArticleId();
        log.info("lastArticleId = {}", lastArticleId);
        articles = articleQueryRepository.findAllInfiniteScroll(1L, 30L, lastArticleId);
        log.info("articles.size = {}", articles.size());
    }

}
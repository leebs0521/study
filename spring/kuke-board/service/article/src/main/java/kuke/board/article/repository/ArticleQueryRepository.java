package kuke.board.article.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kuke.board.article.entity.Article;
import kuke.board.article.entity.QArticle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ArticleQueryRepository {

    private final JPAQueryFactory queryFactory;

    private static final QArticle article = QArticle.article;

    public List<Article> findAll(Long boardId, Long offset, Long limit) {
        // 1단계: 서브쿼리로 페이징된 ID 목록 조회 (커버링 인덱스 활용)
        List<Long> articleIds = queryFactory
                .select(article.articleId)
                .from(article)
                .where(article.boardId.eq(boardId))
                .orderBy(article.articleId.desc())
                .offset(offset)
                .limit(limit)
                .fetch();

        // ID 목록이 비어있으면 빈 리스트 반환
        if (articleIds.isEmpty()) {
            return List.of();
        }

        // 2단계: ID 목록으로 전체 엔티티 조회
        return queryFactory
                .selectFrom(article)
                .where(article.articleId.in(articleIds))
                .orderBy(article.articleId.desc())
                .fetch();
    }

    public Long count(Long boardId, Long limit) {
        List<Long> ids = queryFactory
                .select(article.articleId)
                .from(article)
                .where(article.boardId.eq(boardId))
                .limit(limit)
                .fetch();

        return (long) ids.size();
    }

    public List<Article> findAllInfiniteScroll(Long boardId, Long limit) {
        return queryFactory
                .selectFrom(article)
                .where(article.boardId.eq(boardId))
                .orderBy(article.articleId.desc())
                .limit(limit)
                .fetch();
    }

    public List<Article> findAllInfiniteScroll(Long boardId, Long limit, Long lastArticleId) {
        return queryFactory
                .selectFrom(article)
                .where(article.boardId.eq(boardId), article.articleId.lt(lastArticleId))
                .orderBy(article.articleId.desc())
                .limit(limit)
                .fetch();
    }

}

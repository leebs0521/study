package kuke.board.article.service.response;

import java.util.List;

public record ArticlePageResponse(
        List<ArticleResponse> articles,
        Long articleCount
) {

    public static ArticlePageResponse of(List<ArticleResponse> articles, Long articleCount) {
        return new ArticlePageResponse(articles, articleCount);
    }
}

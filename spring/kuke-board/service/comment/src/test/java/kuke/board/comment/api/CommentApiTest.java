package kuke.board.comment.api;

import kuke.board.comment.service.request.CommentCreateRequest;
import kuke.board.comment.service.response.CommentPageResponse;
import kuke.board.comment.service.response.CommentResponse;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

public class CommentApiTest {

    RestClient restClient = RestClient.create("http://localhost:9002");

    @Test
    void create() {
        CommentResponse comment1 = createComment(new CommentCreateRequest(1L, "my content1", null, 1L));
        CommentResponse comment2 = createComment(new CommentCreateRequest(1L, "my content2", comment1.commentId(), 1L));
        CommentResponse comment3 = createComment(new CommentCreateRequest(1L, "my content3", comment1.commentId(), 1L));
        System.out.println("comment1 = " + comment1);
        System.out.println("comment2 = " + comment2);
        System.out.println("comment3 = " + comment3);
    }

    CommentResponse createComment(CommentCreateRequest request) {
        return restClient.post()
                .uri("/v1/comments")
                .body(request)
                .retrieve()
                .body(CommentResponse.class);
    }

    @Test
    void read() {
        CommentResponse response = restClient.get()
                .uri("/v1/comments/{commentId}", 233208233675431936L)
                .retrieve()
                .body(CommentResponse.class);

        System.out.println("response = " + response);
    }

    @Test
    void delete() {
        //        commentId=233208233675431936 - x
        //          commentId=233208234052919296 - x
        //          commentId=233208234082279424 - x

        restClient.delete()
                .uri("/v1/comments/{commentId}", 233208233675431936L)
                .retrieve();
    }

    @Test
    void readAll() {
        CommentPageResponse response = restClient.get()
                .uri("/v1/comments?articleId=1&page=1&pageSize=10")
                .retrieve()
                .body(CommentPageResponse.class);

        System.out.println("response.getCommentCount() = " + response.commentCount());
        for (CommentResponse comment : response.comments()) {
            if (!comment.commentId().equals(comment.parentCommentId())) {
                System.out.print("\t");
            }
            System.out.println("comment.getCommentId() = " + comment.commentId());
        }
    }

    @Test
    void readAllInfiniteScroll() {
        List<CommentResponse> responses1 = restClient.get()
                .uri("/v1/comments/infinite-scroll?articleId=1&pageSize=5")
                .retrieve()
                .body(new ParameterizedTypeReference<List<CommentResponse>>() {
                });

        System.out.println("firstPage");
        for (CommentResponse comment : responses1) {
            if (!comment.commentId().equals(comment.parentCommentId())) {
                System.out.print("\t");
            }
            System.out.println("comment.getCommentId() = " + comment.commentId());
        }

        Long lastParentCommentId = responses1.getLast().parentCommentId();
        Long lastCommentId = responses1.getLast().commentId();

        List<CommentResponse> responses2 = restClient.get()
                .uri("/v1/comments/infinite-scroll?articleId=1&pageSize=5&lastParentCommentId=%s&lastCommentId=%s"
                        .formatted(lastParentCommentId, lastCommentId))
                .retrieve()
                .body(new ParameterizedTypeReference<List<CommentResponse>>() {
                });

        System.out.println("secondPage");
        for (CommentResponse comment : responses2) {
            if (!comment.commentId().equals(comment.parentCommentId())) {
                System.out.print("\t");
            }
            System.out.println("comment.getCommentId() = " + comment.commentId());
        }
    }
}

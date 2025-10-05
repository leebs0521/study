package kuke.board.comment.api;

import kuke.board.comment.service.request.CommentCreateRequestV2;
import kuke.board.comment.service.response.CommentPageResponse;
import kuke.board.comment.service.response.CommentResponse;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

public class CommentApiV2Test {
    RestClient restClient = RestClient.create("http://localhost:9002");

    @Test
    void create() {
        CommentResponse response1 = create(new CommentCreateRequestV2(1L, "my comment1", null, 1L));
        CommentResponse response2 = create(new CommentCreateRequestV2(1L, "my comment2", response1.path(), 1L));
        CommentResponse response3 = create(new CommentCreateRequestV2(1L, "my comment3", response2.path(), 1L));

        System.out.println("response1.getPath() = " + response1.path());
        System.out.println("response1.getCommentId() = " + response1.commentId());
        System.out.println("\tresponse2.getPath() = " + response2.path());
        System.out.println("\tresponse2.getCommentId() = " + response2.commentId());
        System.out.println("\t\tresponse3.getPath() = " + response3.path());
        System.out.println("\t\tresponse3.getCommentId() = " + response3.commentId());

//        response1.getPath() = 00000
//        response1.getCommentId() = 233243293788532736
//        	response2.getPath() = 0000000000
//	        response2.getCommentId() = 233243294719668224
//		        response3.getPath() = 000000000000000
//		        response3.getCommentId() = 233243294749028352
    }

    CommentResponse create(CommentCreateRequestV2 request) {
        return restClient.post()
                .uri("/v2/comments")
                .body(request)
                .retrieve()
                .body(CommentResponse.class);
    }

    @Test
    void read() {
        CommentResponse response = restClient.get()
                .uri("/v2/comments/{commentId}", 233243293788532736L)
                .retrieve()
                .body(CommentResponse.class);
        System.out.println("response = " + response);
    }

    @Test
    void delete() {
        restClient.delete()
                .uri("/v2/comments/{commentId}", 233243293788532736L)
                .retrieve();
    }

    @Test
    void readAll() {
        CommentPageResponse response = restClient.get()
                .uri("/v2/comments?articleId=1&pageSize=10&page=1")
                .retrieve()
                .body(CommentPageResponse.class);

        System.out.println("response.getCommentCount() = " + response.commentCount());
        for (CommentResponse comment : response.comments()) {
            System.out.println("comment.getCommentId() = " + comment.commentId());
        }

        /**
         * comment.getCommentId() = 124136527842209792
         * comment.getCommentId() = 124136528337137664
         * comment.getCommentId() = 124136528408440832
         * comment.getCommentId() = 124136572368941056
         * comment.getCommentId() = 124136572561879040
         * comment.getCommentId() = 124136572616404992
         * comment.getCommentId() = 124136618837635072
         * comment.getCommentId() = 124136619009601536
         * comment.getCommentId() = 124136619068321792
         * comment.getCommentId() = 124136886845272064
         */
    }

    @Test
    void readAllInfiniteScroll() {
        List<CommentResponse> responses1 = restClient.get()
                .uri("/v2/comments/infinite-scroll?articleId=1&pageSize=5")
                .retrieve()
                .body(new ParameterizedTypeReference<List<CommentResponse>>() {
                });

        System.out.println("firstPage");
        for (CommentResponse response : responses1) {
            System.out.println("response.getCommentId() = " + response.commentId());
        }

        String lastPath = responses1.getLast().path();
        List<CommentResponse> responses2 = restClient.get()
                .uri("/v2/comments/infinite-scroll?articleId=1&pageSize=5&lastPath=%s".formatted(lastPath))
                .retrieve()
                .body(new ParameterizedTypeReference<List<CommentResponse>>() {
                });

        System.out.println("secondPage");
        for (CommentResponse response : responses2) {
            System.out.println("response.getCommentId() = " + response.commentId());
        }
    }

}

package o.restApi;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

public class GetSpring5WithPOJO {
    private WebTestClient client;
    private String baseURL;// а endpoint идет отдельно
    @Test
    void name() {
        baseURL = "https://jsonview.com/";
        client = WebTestClient.bindToServer()
                .baseUrl(baseURL)
                .build();

        EntityExchangeResult<String> result = client.get()
                .uri("/example.json")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .returnResult();
        System.out.println(result.getResponseBody());
    }
}

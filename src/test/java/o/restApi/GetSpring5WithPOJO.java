package o.restApi;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import p.JsonPages.Anobject;
import p.JsonPages.ExamplePage;
import p.JsonPages.TimeZonePage;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetSpring5WithPOJO {
    private WebTestClient client;
    private String baseURL;// а endpoint идет отдельно

    @Test
    void GETWithPOJO1() {
        baseURL = "http://worldtimeapi.org/api/timezone";
        client = WebTestClient.bindToServer()
                .baseUrl(baseURL)
                .build();

        EntityExchangeResult<TimeZonePage> result = client.get()
                .uri("/America/Los_Angeles")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(TimeZonePage.class) // важно чтобы у созданного класса были одноименные переменные
                .returnResult();
        System.out.println(result.getResponseBody());

        TimeZonePage response = result.getResponseBody();
        assertEquals(227, response.getDay_of_year());
        assertEquals("PDT", response.getAbbreviation());
        assertEquals(33, response.getWeek_number());

    }

    @Test
    void GETWithPOJO2() {
        baseURL = "https://jsonview.com/";
        client = WebTestClient.bindToServer()
                .baseUrl(baseURL)
                .build();

        EntityExchangeResult<ExamplePage> result = client.get() // каким методом
                .uri("/example.json") // куда: endpoint и ЕСЛИ С ПАРАМЕТРАМИ, то сюда же добавить
                .exchange()// отправка запроса!
/*
.expectStatus() Эти 2 строки это просто проверка, А ВОТ ВСЕ ОСТАЛЬНЫЕ команды ОБЯЗАТЕЛЬНЫ
.isOk()
*/
                .expectBody(ExamplePage.class) // обязательна, иначе не знает в каком види возвращать
                .returnResult();
       // System.out.println(result.getResponseBody());

        // I. Verify Status Code
        int statusCode = result.getStatus().value();
        System.out.println("Status code: " + statusCode);

        /* II. Verify response HEADERS*/

        // Verify that Content-Type is
        MediaType responseContentType = result.getResponseHeaders()
                                                .getContentType();
        System.out.println(responseContentType);

        // Verify that Content-Length is ...bytes
        long responseContentLength = result.getResponseHeaders()
                .getContentLength();
        System.out.println(responseContentLength);

        // III. Verify body elements
        ExamplePage body = result.getResponseBody();
        System.out.println(body.getJapanese());

        Anobject anobject = body.getAnobject();

        String found_more = anobject.getMore();
        System.out.println("found_more: " + found_more);

         ArrayList<Object> found_anarray = body.getAnobject().getAnarray();
         Object first_elem = found_anarray.get(0);
        System.out.println(first_elem);




    }
}

package o.restApi;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class GetSpring5Test {

    private WebTestClient client;
    private String baseURL;// а endpoint идет отдельно

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void GetWithoutParams() {
        baseURL = "https://api.zippopotam.us";

        /* Create an instance клиента связанного с сервером*/
        client = WebTestClient.bindToServer()
                                .baseUrl(baseURL)
                                .build(); // подключиться к серверу, теперь наш клиент готов послать реквесты

        /* на каждый request method у него есть метод as
        instance нашего класса WebTestClient implements WebClient interface,
        so WebClient interface has method get();
         */

        /* класс EntityExchangeResult отобразит response в тот класс который мы зададим*/
         EntityExchangeResult <String> result = client.get()// Start building an HTTP GET request, returns RequestHeadersUriSpec interface
        .uri("/us/33713")
                .accept(MediaType.APPLICATION_JSON) // Set the list of acceptable media types, as specified by the Accept header
                .exchange() // Perform the HTTP request!!! RETURNS a ClientResponse interface with the response status and headers. You can then use methods of the response to consume the body
                .expectStatus()// проверить КОД который вернулся (это метод из Interface WebTestClient.ResponseSpec returns Class StatusAssertions)
        .is2xxSuccessful()// in the 2xx range или конкретно isOk()= 200
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(String.class) // Then you specify how to decode (and consume later) the response body
        .returnResult();

        // Берем только body(не весь response). result это String можно и его весь распечатать
        String body = result.getResponseBody();

        // теперь распечатаем body
        System.out.println(result);
    }

    @Test
    void GetWithParams(){
        baseURL = "https://api.openweathermap.org";
        client = WebTestClient.bindToServer()
                .baseUrl(baseURL)
                .build();

        String city = "Prague";
        String appid = "43cdae934aa47d0a4a20f0b05177e8b0";

        EntityExchangeResult<String> result = client.get()
                .uri("/data/2.5/weather?q={city}&appid={appid}", city, appid) // добавляем параметры тут
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .consumeWith(response -> assertFalse( response.getResponseBody().isEmpty() )) // проверяем что ответ не пустой
                .returnResult();

        System.out.println(result);

    }
}

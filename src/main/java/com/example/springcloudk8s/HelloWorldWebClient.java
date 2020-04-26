package com.example.springcloudk8s;



import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * WebClient uses the Reactive Streams API. WebClient has replaced RestTemplate, which is deprecated as of Spring 4.3.
 *
 * Although WebClient supports asynchronous REST calls, the WebClient in ModuleARestClient
 * is used in a synchronous manner and blocks until it receives a response.
 */
@Component
public class HelloWorldWebClient {

    private WebClient webClient;

    public String getMessage(String uri) {
        System.out.println("uri="+uri);
        webClient = WebClient
                .builder()
                .exchangeStrategies( // Explicitly enable header logging. By default, headers are masked.
                        ExchangeStrategies.builder().codecs(c -> c.defaultCodecs().enableLoggingRequestDetails(true)).build())
                .baseUrl(uri)
                .build();
        String message = webClient.get().retrieve().bodyToMono(String.class).block();
        System.out.println("message="+message);
        return message;
    }

}

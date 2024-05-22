package br.com.unifacisa.msmain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@Slf4j
@RequestMapping("/main")
public class MainController {
    private final RestClient restClient;
    public MainController(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("http://localhost:8000")
                .build();
    }
    @GetMapping
    public String getMainRequest() {
        var response = restClient
                .get()
                .uri("/request")
                .retrieve()
                .toBodilessEntity();

        log.info("Response: {}; Thread: {}", response.getStatusCode(), Thread.currentThread());
        return Thread.currentThread().toString();
    }
}

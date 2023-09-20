package empapp.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@Slf4j
public class KafkaConfiguration {

    @Bean
    public Function<HelloRequest, HelloResponse> handleRequest() {
        return request -> {
            log.info("Request has come: {}", request);
            return new HelloResponse("Hello %s!".formatted(request.getName()));
        };
    }
}

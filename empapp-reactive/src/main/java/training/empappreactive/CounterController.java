package training.empappreactive;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@RestController
public class CounterController {

    @GetMapping(path =  "/counters", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> count() {
        return Flux
                .interval(Duration.of(1, ChronoUnit.SECONDS))
                .map("Hello - %d"::formatted);
    }

}

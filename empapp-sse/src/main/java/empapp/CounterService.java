package empapp;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.stream.IntStream;

@Service
@Slf4j
public class CounterService {

    @Async
    public void doCount(SseEmitter emitter) {
        log.info("doCount start");

        IntStream.range(0, 11)
                .peek(this::sleep)
                .forEach(i -> sendNumber(emitter, i));
        log.info("doCount end");
    }

    @SneakyThrows
    private void sendNumber(SseEmitter emitter, int i) {
        emitter.send("Hello %d".formatted(i));
    }

    @SneakyThrows
    private void sleep(int i) {
        Thread.sleep(1000);
    }
}

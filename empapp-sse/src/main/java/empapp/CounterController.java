package empapp;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.stream.IntStream;

@Controller
@RequestMapping("/api/counter")
@Slf4j
@AllArgsConstructor
public class CounterController {

    private CounterService counterService;

    @GetMapping
    public SseEmitter count() {
        log.info("count start: {}", counterService.getClass().getName());
        var emitter = new SseEmitter();
        counterService.doCount(emitter);
        log.info("count end");
        return emitter;
    }



}

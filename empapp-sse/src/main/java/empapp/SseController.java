package empapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/event")
@Slf4j
public class SseController {

    private List<SseEmitter> emitters = new ArrayList<>();

    @EventListener
    public void handleEvent(Message message) {
        List<SseEmitter> emittersToRemove = new ArrayList<>();
        for (var emitter: emitters) {
            try {

                var builder = SseEmitter.event()
                        .name("message")
                        .id(UUID.randomUUID().toString())
                        .comment("Beautiful message")
                        .data(message);

                emitter.send(builder);
            }
            catch (Throwable t) {
                log.error("Invalid emitter", t);
                emittersToRemove.add(emitter);
            }
        }
        emitters.removeAll(emittersToRemove);
    }


    @GetMapping
    public SseEmitter registerEmitter() {
        var emitter = new SseEmitter();
        emitters.add(emitter);
        return emitter;
    }

}

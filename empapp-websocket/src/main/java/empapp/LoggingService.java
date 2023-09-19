package empapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoggingService {

    @EventListener
    public void handleEvent(MessageResponse messageResponse) {
        log.info("Event has come2: {}", messageResponse);
    }
}

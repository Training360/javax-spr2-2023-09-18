package empapp;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LongRunningProcessService {

    @SneakyThrows
    @Async
    public void run() {
        log.info("Starting long process: {}", Thread.currentThread().getName());
        Thread.sleep(5_000);
        log.info("Long process end");
    }
}

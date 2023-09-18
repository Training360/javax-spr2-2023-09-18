package empapp;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@AllArgsConstructor
public class LongRunningProcessService {

    @SneakyThrows
    @Async
    public void run() {
        log.info("Starting long process: {}", Thread.currentThread().getName());
        Thread.sleep(5_000);
        log.info("Long process end");
    }

    @Scheduled(fixedRate = 5, initialDelay = 2, timeUnit = TimeUnit.SECONDS)
    public void tick() {
        log.info("Tick");
    }
}

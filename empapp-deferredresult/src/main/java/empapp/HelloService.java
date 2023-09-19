package empapp;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
@Slf4j
public class HelloService {
    @SneakyThrows
    @Async
    public void sayHello(DeferredResult<String> result) {
        log.info("Start long process");
        Thread.sleep(5000);
        log.info("End long process");
        result.setResult("Hello!");
    }
}

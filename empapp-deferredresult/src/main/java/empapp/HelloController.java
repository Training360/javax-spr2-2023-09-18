package empapp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@AllArgsConstructor
@Slf4j
public class HelloController {

    private HelloService helloService;

    @GetMapping("/api/hello")
    public DeferredResult<String> sayHello() {
        log.info("Start sayHello");
        DeferredResult<String> deferredResult = new DeferredResult<>();
        helloService.sayHello(deferredResult);
        log.info("End sayHello");
        return deferredResult;
    }
}

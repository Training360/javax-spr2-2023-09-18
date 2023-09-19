package empapp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeesWebSocketController {

    @MessageMapping("/messages")
    @SendTo("/topic/employees")
    public MessageResponse handleMessage(MessageRequest request) {
        return new MessageResponse("Hello " + request.getRequestText());
    }
}

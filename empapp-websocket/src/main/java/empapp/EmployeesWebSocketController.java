package empapp;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class EmployeesWebSocketController {

    private SimpMessagingTemplate template;

    @EventListener
    public void handleEvent(MessageResponse messageResponse) {
        template.convertAndSend("/topic/employees", messageResponse);
    }

    @MessageMapping("/messages")
    @SendTo("/topic/employees")
    public MessageResponse handleMessage(MessageRequest request) {
        return new MessageResponse("Hello " + request.getRequestText());
    }
}

package empapp;

import empapp.entity.Employee;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class JmsService {

    private JmsTemplate jmsTemplate;

    public void send(Employee employee) {
        jmsTemplate.send("employeesQueue",
                s -> s.createTextMessage("Employee has been created: %s".formatted(employee.getName())));
    }

    @JmsListener(destination = "employeesQueue")
    @SneakyThrows
    public void receive(Message message) {
        if (message instanceof TextMessage textMessage) {
            log.info("JMS message has come: {}", textMessage.getText());
        }
        else {
            log.error("Unknown message: {}", message.getClass().getName());
        }
    }
}

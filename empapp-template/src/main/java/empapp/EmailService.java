package empapp;

import empapp.entity.Employee;
import jakarta.mail.Message;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Locale;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class EmailService {

    private SpringTemplateEngine engine;

    private JavaMailSender mailSender;

    @SneakyThrows
    public void send(Employee employee) {
        var text = engine.process("mail",
                new Context(Locale.ENGLISH, Map.of("employee", employee)));
        log.info("Document: {}", text);

        var message = mailSender.createMimeMessage();

//        message.setRecipients(Message.RecipientType.TO, "admin@employees.com");

        var helper = new MimeMessageHelper(message);
        helper.setTo("admin@employees.com");
        helper.setFrom("admin@employees.com");
        helper.setSubject("árvíztűrő tükörfúrógép");
        helper.setText(text, true);

        mailSender.send(message);
    }
}

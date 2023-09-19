package training.empappwebsocketclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@Slf4j
public class EmpappWebsocketClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EmpappWebsocketClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var client = new WebSocketStompClient(new SockJsClient(List.of(new WebSocketTransport(new StandardWebSocketClient()))));
		client.setMessageConverter(new MappingJackson2MessageConverter());
		var result = client.connectAsync("ws://localhost:8080/websocket-endpoint", new StompSessionHandlerAdapter() {
			@Override
			public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
				session.subscribe("/topic/employees", this);
			}

			@Override
			public Type getPayloadType(StompHeaders headers) {
				return MessageResponse.class;
			}

			@Override
			public void handleFrame(StompHeaders headers, Object payload) {
				if (payload instanceof MessageResponse response) {
					log.info("Message has come: {}", response.getResponseText());
				}
				else  {
					throw new IllegalStateException("Invalid class: %s".formatted(payload.getClass().getName()));
				}
			}

			@Override
			public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
				log.error("exception", exception);
			}

			@Override
			public void handleTransportError(StompSession session, Throwable exception) {
				log.error("transport", exception);
			}
		});

		result.whenComplete((session, t) -> {
			try {
				Thread.sleep(5000);
			}catch (InterruptedException ie) {
				log.error("interrupt", ie);
			}
			log.info("Sending message");
			session.send("/app/messages", new MessageRequest("heelo, i'm here"));
			}
		);

		log.info("start scanner");
		var scanner = new Scanner(System.in);
		scanner.nextLine();
	}
}

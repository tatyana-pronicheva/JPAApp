package com.example.JPAApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@SpringBootApplication
@EnableCaching
public class JpaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaAppApplication.class, args);
	}

	@Configuration
	@EnableWebSocketMessageBroker
	public class WebSocketConfig extends
			AbstractWebSocketMessageBrokerConfigurer {
		@Override
		public void registerStompEndpoints(StompEndpointRegistry registry) {
			registry.addEndpoint("/socket").withSockJS();
		}
		@Override
		public void configureMessageBroker(MessageBrokerRegistry config) {
			config.enableSimpleBroker("/topic");
			config.setApplicationDestinationPrefixes("/app");
		}
	}
}

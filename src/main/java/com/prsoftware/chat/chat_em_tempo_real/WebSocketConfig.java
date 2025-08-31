package com.prsoftware.chat.chat_em_tempo_real;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.lang.NonNull;

@Configuration //Anotação de Configuração da Classe
@EnableWebSocketMessageBroker //Iniciando a Anotação do Web MessageBox
public class WebSocketConfig  implements WebSocketMessageBrokerConfigurer{

    @Override //Criando uma Classe para Registrar o StompEndopoint
    public void registerStompEndpoints(@NonNull StompEndpointRegistry registry){
        registry.addEndpoint("/chat-websocket")
            .setAllowedOriginPatterns("*")
            .withSockJS();

    }

    @Override //Criando uma Classe para a Configuração do Broker
    public void configureMessageBroker(@NonNull MessageBrokerRegistry registry){
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");

    }

}

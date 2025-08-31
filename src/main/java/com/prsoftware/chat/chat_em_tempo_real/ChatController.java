package com.prsoftware.chat.chat_em_tempo_real;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


//Criando o Controller da Mensagem
@Controller
public class ChatController {

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public Message send(Message message){
        return message;
    }

}

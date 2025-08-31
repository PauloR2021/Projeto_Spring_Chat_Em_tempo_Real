package com.prsoftware.chat.chat_em_tempo_real;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatController {

    //Variavel para salvar o arquivo 
    private static final String FILE_PATH = "chat_history.txt";

    // Envia a mensagem via WebSocket e salva no arquivo
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public Message send(Message message){
        saveMessageToFile(message);
        return message;
    }

    // Endpoint para finalizar o chat
    @PostMapping(value = "/chat/finalizar", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, String> finalizarChat(){
        Map<String, String> response = new HashMap<>();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write("\n========== Chat finalizado em " + LocalDateTime.now() + " ==========\n");
            response.put("status", "success");
            response.put("message", "Chat finalizado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            response.put("status", "error");
            response.put("message", "Erro ao finalizar o chat.");
        }
        return response;
    }

    // Salva cada mensagem no arquivo
    private void saveMessageToFile(Message message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String line = String.format("[%s] %s: %s%n",
                    LocalDateTime.now(),
                    message.getSender() != null ? message.getSender() : "Desconhecido",
                    message.getContent() != null ? message.getContent() : "");
            writer.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

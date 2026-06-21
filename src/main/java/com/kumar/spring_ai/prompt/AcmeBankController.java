package com.kumar.spring_ai.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/bank")
@RestController
public class AcmeBankController {

    private final ChatClient chatClient;

    public AcmeBankController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {

        String systeminstruction = """
                You can ONLY discuss:
                - Account balances and transactions
                - Branch locations and hours
                - General banking services
                
                If asked about anything else, respond:
                I can only help with banking-related questions.
                """;

        return chatClient.prompt()
                .user(message)
                .system(systeminstruction) //guard of front door, use system instruction to restrict the model's responses to banking-related topics
                .call()
                .content();
    }

}

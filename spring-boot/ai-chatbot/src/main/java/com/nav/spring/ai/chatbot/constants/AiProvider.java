package com.nav.spring.ai.chatbot.constants;

import java.util.Arrays;

public enum AiProvider {

    OPENAI("OpenAI"),
    OLLAMA("ollama");

    private String label;

    private AiProvider(String label){
        this.label = label;
    }

    public static AiProvider getInstance(String label) {
        return Arrays.stream(AiProvider.values())
                .filter(aiProvider -> aiProvider.label.equalsIgnoreCase(label))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("The given provider is not supported."));
    }
}

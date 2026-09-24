package com.clipgrowth.clipgrowth_backend.ai;

import org.springframework.stereotype.Component;

@Component
public class MockAiProvider implements AiProvider {

    @Override
    public String getName() {
        return "mock";
    }

    @Override
    public AiResponse generate(AiRequest request) {
        return new AiResponse(
                "Mock AI response for task: " + request.task()
        );
    }
}
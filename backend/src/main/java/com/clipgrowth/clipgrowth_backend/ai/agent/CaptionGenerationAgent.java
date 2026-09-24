package com.clipgrowth.clipgrowth_backend.ai.agent;

import org.springframework.stereotype.Component;

@Component
public class CaptionGenerationAgent implements AiAgent {

    @Override
    public String getName() {
        return "caption-generation";
    }

    @Override
    public AgentResult execute(AgentTask task) {

        return new AgentResult(
                true,
                "Caption generation pending AI provider integration"
        );
    }
}
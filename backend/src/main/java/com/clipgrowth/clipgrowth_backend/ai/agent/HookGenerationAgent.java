package com.clipgrowth.clipgrowth_backend.ai.agent;

import org.springframework.stereotype.Component;

@Component
public class HookGenerationAgent implements AiAgent {

    @Override
    public String getName() {
        return "hook-generation";
    }

    @Override
    public AgentResult execute(AgentTask task) {

        return new AgentResult(
                true,
                "Hook generation pending AI provider integration"
        );
    }
}
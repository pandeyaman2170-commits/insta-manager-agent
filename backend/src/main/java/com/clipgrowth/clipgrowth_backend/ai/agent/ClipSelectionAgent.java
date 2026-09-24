package com.clipgrowth.clipgrowth_backend.ai.agent;

import org.springframework.stereotype.Component;

@Component
public class ClipSelectionAgent implements AiAgent {

    @Override
    public String getName() {
        return "clip-selection";
    }

    @Override
    public AgentResult execute(AgentTask task) {

        return new AgentResult(
                true,
                "Clip selection pending AI provider integration"
        );
    }
}
package com.clipgrowth.clipgrowth_backend.ai.agent;

import org.springframework.stereotype.Component;

@Component
public class ContentAnalysisAgent implements AiAgent {

    @Override
    public String getName() {
        return "content-analysis";
    }

    @Override
    public AgentResult execute(AgentTask task) {
        return new AgentResult(
                true,
                task.input() + "\nContent analysis completed"
        );
    }
}
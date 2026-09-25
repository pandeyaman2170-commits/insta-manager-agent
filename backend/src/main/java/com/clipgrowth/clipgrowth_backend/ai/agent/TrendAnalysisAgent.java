package com.clipgrowth.clipgrowth_backend.ai.agent;

import org.springframework.stereotype.Component;

@Component
public class TrendAnalysisAgent implements AiAgent {

    @Override
    public String getName() {
        return "trend-analysis";
    }

    @Override
    public AgentResult execute(AgentTask task) {
        return new AgentResult(
                true,
                task.input() + "\nTrend analysis completed"
        );
    }
}
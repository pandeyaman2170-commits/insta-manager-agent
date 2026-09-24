package com.clipgrowth.clipgrowth_backend.ai.agent;

import org.springframework.stereotype.Component;

@Component
public class VideoAnalysisAgent implements AiAgent {

    @Override
    public String getName() {
        return "video-analysis";
    }

    @Override
    public AgentResult execute(AgentTask task) {

        return new AgentResult(
                true,
                "Video analysis pending AI provider integration"
        );
    }
}
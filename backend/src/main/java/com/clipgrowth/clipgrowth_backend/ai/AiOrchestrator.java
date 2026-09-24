package com.clipgrowth.clipgrowth_backend.ai;

import com.clipgrowth.clipgrowth_backend.ai.agent.AgentResult;
import com.clipgrowth.clipgrowth_backend.ai.agent.AgentTask;
import com.clipgrowth.clipgrowth_backend.ai.agent.AiAgent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiOrchestrator {

    private final List<AiAgent> agents;
    private final List<AiProvider> providers;

    public AiOrchestrator(
            List<AiAgent> agents,
            List<AiProvider> providers) {

        this.agents = agents;
        this.providers = providers;
    }

    public AgentResult execute(String agentName, AgentTask task) {

        AiAgent agent = agents.stream()
                .filter(a -> a.getName().equals(agentName))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "AI agent not found: " + agentName));

        return agent.execute(task);
    }

    public AiResponse generate(AiRequest request) {

        AiProvider provider = providers.stream()
                .filter(p -> p.getName().equals("mock"))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException(
                                "No AI provider configured"));

        return provider.generate(request);
    }
}
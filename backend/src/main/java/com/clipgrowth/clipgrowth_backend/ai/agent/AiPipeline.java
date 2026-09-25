package com.clipgrowth.clipgrowth_backend.ai.agent;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiPipeline {

    private final List<AiAgent> agents;

    public AiPipeline(List<AiAgent> agents) {
        this.agents = agents;
    }

    public AgentResult execute(AgentTask task) {

        String currentInput = task.input();

        for (AiAgent agent : agents) {

            AgentResult result = agent.execute(
                    new AgentTask(
                            task.taskType(),
                            currentInput
                    )
            );

            if (!result.success()) {
                return result;
            }

            currentInput = result.output();
        }

        return new AgentResult(true, currentInput);
    }
}
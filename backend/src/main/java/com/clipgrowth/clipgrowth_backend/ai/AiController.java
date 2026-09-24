package com.clipgrowth.clipgrowth_backend.ai;

import com.clipgrowth.clipgrowth_backend.ai.agent.AgentResult;
import com.clipgrowth.clipgrowth_backend.ai.agent.AgentTask;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiOrchestrator aiOrchestrator;

    public AiController(AiOrchestrator aiOrchestrator) {
        this.aiOrchestrator = aiOrchestrator;
    }

    @PostMapping("/agents/{agentName}")
    public AgentResult executeAgent(
            @PathVariable String agentName,
            @RequestBody AgentTask task) {

        return aiOrchestrator.execute(agentName, task);
    }
    @PostMapping("/generate")
    public AiResponse generate(@RequestBody AiRequest request) {
        return aiOrchestrator.generate(request);
    }
}
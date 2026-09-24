package com.clipgrowth.clipgrowth_backend.ai.agent;

public interface AiAgent {

    String getName();

    AgentResult execute(AgentTask task);
}
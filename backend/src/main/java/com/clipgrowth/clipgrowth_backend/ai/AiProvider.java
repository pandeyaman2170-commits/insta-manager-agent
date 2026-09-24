package com.clipgrowth.clipgrowth_backend.ai;

public interface AiProvider {

    String getName();

    AiResponse generate(AiRequest request);
}
package com.clipgrowth.clipgrowth_backend.analysis;

public record AnalysisResult(
        String transcript,
        String summary,
        String trends,
        String hooks,
        String clipRecommendations,
        String captions
) {
}
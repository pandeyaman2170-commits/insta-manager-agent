package com.clipgrowth.clipgrowth_backend.analysis;

import com.clipgrowth.clipgrowth_backend.ai.agent.AiPipeline;
import com.clipgrowth.clipgrowth_backend.ai.agent.AgentResult;
import com.clipgrowth.clipgrowth_backend.ai.agent.AgentTask;
import org.springframework.stereotype.Service;
import com.clipgrowth.clipgrowth_backend.clip.*;

@Service
public class AnalysisPipelineService {

    private final AiPipeline aiPipeline;
    private final ClipGenerationService clipGenerationService;

    public AnalysisPipelineService(
            AiPipeline aiPipeline,
            ClipGenerationService clipGenerationService) {

        this.aiPipeline = aiPipeline;
        this.clipGenerationService = clipGenerationService;
    }

    public AnalysisResult analyze(VideoAnalysis analysis) {

        String input = analysis.getTranscript();

        if (input == null || input.isBlank()) {
            input = "Video analysis input pending transcription";
        }

        AgentResult result = aiPipeline.execute(
                new AgentTask(
                        "VIDEO_ANALYSIS",
                        input
                )
        );
        Clip clip = clipGenerationService.createCandidate(
            analysis.getVideo(),
            0,
            30,
            "AI Candidate Clip",
            analysis.getHooks(),
            0.0
        );
analysis.setClipRecommendations(clip.getHook());
analysis.setCaptions(clip.getCaption());
analysis.setStatus(AnalysisStatus.COMPLETED);
analysisRepository.save(analysis);
        return new AnalysisResult(
                input,
                result.output(),
                "",
                "",
                "",
                ""
        );
    }
}
package com.clipgrowth.clipgrowth_backend.clip;

import com.clipgrowth.clipgrowth_backend.video.Video;
import org.springframework.stereotype.Service;
import com.clipgrowth.clipgrowth_backend.ai.agent.AiPipeline;
import com.clipgrowth.clipgrowth_backend.ai.agent.AgentResult;
import com.clipgrowth.clipgrowth_backend.ai.agent.AgentTask;

@Service
public class ClipGenerationService {

    private final ClipRepository clipRepository;
    private final AiPipeline aiPipeline;

    public ClipGenerationService(
            ClipRepository clipRepository,
            AiPipeline aiPipeline) {
        this.clipRepository = clipRepository;
        this.aiPipeline = aiPipeline;
    }

    public Clip createCandidate(
            Video video,
            long startTimeSeconds,
            long endTimeSeconds,
            String title,
            String hook,
            double predictedScore) {
        AgentResult result = aiPipeline.execute(
                new AgentTask(
                        "CLIP_ANALYSIS",
                        "Analyze video clip from " +
                                startTimeSeconds +
                                " to " +
                                endTimeSeconds
                )
        );
        Clip clip = new Clip();

        clip.setSourceVideo(video);
        clip.setStartTimeSeconds(startTimeSeconds);
        clip.setEndTimeSeconds(endTimeSeconds);
        clip.setTitle(title);
        clip.setHook(hook);
        clip.setPredictedScore(predictedScore);
        clip.setStatus(ClipStatus.CANDIDATE);

        return clipRepository.save(clip);
    }
}
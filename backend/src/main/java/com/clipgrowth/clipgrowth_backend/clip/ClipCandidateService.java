package com.clipgrowth.clipgrowth_backend.clip;

import com.clipgrowth.clipgrowth_backend.video.Video;
import org.springframework.stereotype.Service;

@Service
public class ClipCandidateService {

    private final ClipRepository clipRepository;

    public ClipCandidateService(ClipRepository clipRepository) {
        this.clipRepository = clipRepository;
    }

    public Clip createCandidate(
            Video video,
            long startTimeSeconds,
            long endTimeSeconds,
            String title,
            String hook,
            String caption,
            double predictedScore) {

        Clip clip = new Clip();

        clip.setSourceVideo(video);
        clip.setStartTimeSeconds(startTimeSeconds);
        clip.setEndTimeSeconds(endTimeSeconds);
        clip.setTitle(title);
        clip.setHook(hook);
        clip.setCaption(caption);
        clip.setPredictedScore(predictedScore);
        clip.setStatus(ClipStatus.CANDIDATE);

        return clipRepository.save(clip);
    }
}
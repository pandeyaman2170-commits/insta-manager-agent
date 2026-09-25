package com.clipgrowth.clipgrowth_backend.analysis;

import com.clipgrowth.clipgrowth_backend.video.Video;
import org.springframework.stereotype.Service;

@Service
public class VideoAnalysisService {

    private final VideoAnalysisRepository repository;

    public VideoAnalysisService(VideoAnalysisRepository repository) {
        this.repository = repository;
    }

    public VideoAnalysis create(Video video) {

        VideoAnalysis analysis = new VideoAnalysis();

        analysis.setVideo(video);
        analysis.setStatus(AnalysisStatus.PENDING);

        return repository.save(analysis);
    }
}
package com.clipgrowth.clipgrowth_backend.processing;

import com.clipgrowth.clipgrowth_backend.analysis.AnalysisStatus;
import com.clipgrowth.clipgrowth_backend.analysis.VideoAnalysis;
import com.clipgrowth.clipgrowth_backend.analysis.VideoAnalysisRepository;
import com.clipgrowth.clipgrowth_backend.video.Video;
import org.springframework.stereotype.Service;

@Service
public class LocalVideoProcessor implements VideoProcessor {

    private final VideoAnalysisRepository videoAnalysisRepository;

    public LocalVideoProcessor(VideoAnalysisRepository videoAnalysisRepository) {
        this.videoAnalysisRepository = videoAnalysisRepository;
    }

    @Override
    public void process(Video video, ProcessingJob job) {

        job.setStatus(ProcessingJobStatus.PROCESSING);

        VideoAnalysis analysis = new VideoAnalysis();
        analysis.setVideo(video);
        analysis.setStatus(AnalysisStatus.PROCESSING);

        videoAnalysisRepository.save(analysis);

        System.out.println("Processing video: " + video.getId());

        // Real transcription/video analysis will be added later.

        analysis.setStatus(AnalysisStatus.COMPLETED);
        videoAnalysisRepository.save(analysis);

        job.setStatus(ProcessingJobStatus.COMPLETED);
    }
}
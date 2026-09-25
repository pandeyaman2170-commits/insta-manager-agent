package com.clipgrowth.clipgrowth_backend.processing;

import com.clipgrowth.clipgrowth_backend.video.Video;
import org.springframework.stereotype.Service;

@Service
public class ProcessingJobService {

    private final ProcessingJobRepository processingJobRepository;
    private final VideoAnalysisService videoAnalysisService;

    public ProcessingJobService(
        ProcessingJobRepository processingJobRepository,
        VideoAnalysisService videoAnalysisService) {

        this.processingJobRepository = processingJobRepository;
        this.videoAnalysisService = videoAnalysisService;
    }

    public ProcessingJob createVideoProcessingJob(Video video) {
        ProcessingJob job = new ProcessingJob();

        job.setVideo(video);
        job.setJobType("VIDEO_ANALYSIS");
        job.setStatus(ProcessingJobStatus.QUEUED);
        videoAnalysisService.create(video);
        return processingJobRepository.save(job);
    }
}
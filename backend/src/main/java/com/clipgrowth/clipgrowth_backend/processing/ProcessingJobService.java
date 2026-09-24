package com.clipgrowth.clipgrowth_backend.processing;

import com.clipgrowth.clipgrowth_backend.video.Video;
import org.springframework.stereotype.Service;

@Service
public class ProcessingJobService {

    private final ProcessingJobRepository processingJobRepository;

    public ProcessingJobService(ProcessingJobRepository processingJobRepository) {
        this.processingJobRepository = processingJobRepository;
    }

    public ProcessingJob createVideoProcessingJob(Video video) {
        ProcessingJob job = new ProcessingJob();

        job.setVideo(video);
        job.setJobType("VIDEO_ANALYSIS");
        job.setStatus(ProcessingJobStatus.QUEUED);

        return processingJobRepository.save(job);
    }
}
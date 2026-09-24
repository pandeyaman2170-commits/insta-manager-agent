package com.clipgrowth.clipgrowth_backend.processing;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProcessingWorker {

    private final ProcessingJobRepository jobRepository;
    private final VideoProcessor videoProcessor;

    public ProcessingWorker(
            ProcessingJobRepository jobRepository,
            VideoProcessor videoProcessor) {
        this.jobRepository = jobRepository;
        this.videoProcessor = videoProcessor;
    }

    @Scheduled(fixedDelay = 5000)
    public void processJobs() {
        jobRepository.findAll().stream()
                .filter(job -> job.getStatus() == ProcessingJobStatus.QUEUED)
                .findFirst()
                .ifPresent(job -> {
                    videoProcessor.process(job.getVideo(), job);
                    jobRepository.save(job);
                });
    }
}
package com.clipgrowth.clipgrowth_backend.processing;

import com.clipgrowth.clipgrowth_backend.analysis.*;
import com.clipgrowth.clipgrowth_backend.analysis.AnalysisStatus;
import com.clipgrowth.clipgrowth_backend.analysis.VideoAnalysis;
import com.clipgrowth.clipgrowth_backend.analysis.VideoAnalysisRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.clipgrowth.clipgrowth_backend.analysis.AnalysisPipelineService;

@Component
public class ProcessingWorker {

    private final ProcessingJobRepository jobRepository;
    private final VideoProcessor videoProcessor;
    private final VideoAnalysisRepository analysisRepository;
    private final AnalysisPipelineService analysisPipelineService;

    public ProcessingWorker(
        ProcessingJobRepository jobRepository,
        VideoProcessor videoProcessor,
        VideoAnalysisRepository analysisRepository,
        AnalysisPipelineService analysisPipelineService) {

        this.jobRepository = jobRepository;
        this.videoProcessor = videoProcessor;
        this.analysisRepository = analysisRepository;
        this.analysisPipelineService = analysisPipelineService;
    }

    @Scheduled(fixedDelay = 5000)
    public void processJobs() {

        jobRepository.findAll().stream()
                .filter(job -> job.getStatus() == ProcessingJobStatus.QUEUED)
                .findFirst()
                .ifPresent(job -> {

                    job.setStatus(ProcessingJobStatus.PROCESSING);
                    jobRepository.save(job);

                    analysisRepository.findAll()
                            .stream()
                            .filter(analysis ->
                                    analysis.getVideo().getId()
                                            .equals(job.getVideo().getId()))
                            .findFirst()
                            .ifPresent(analysis -> {
                                analysis.setStatus(AnalysisStatus.PROCESSING);
                                analysisRepository.save(analysis);
                            });

                    try {
                        videoProcessor.process(job.getVideo(), job);

                        analysisRepository.findAll()
                                .stream()
                                .filter(analysis ->
                                        analysis.getVideo().getId()
                                                .equals(job.getVideo().getId()))
                                .findFirst()
                                .ifPresent(analysis -> {
                                    AnalysisResult result = analysisPipelineService.analyze(analysis);
                                    analysis.setSummary(result.summary());
                                    analysis.setTrends(result.trends());
                                    analysis.setHooks(result.hooks());
                                    analysis.setClipRecommendations(result.clipRecommendations());
                                    analysis.setCaptions(result.captions());
                                    analysis.setStatus(AnalysisStatus.COMPLETED);
                                    analysisRepository.save(analysis);
                                });

                    } catch (Exception e) {

                        job.setStatus(ProcessingJobStatus.FAILED);
                        jobRepository.save(job);

                        analysisRepository.findAll()
                                .stream()
                                .filter(analysis ->
                                        analysis.getVideo().getId()
                                                .equals(job.getVideo().getId()))
                                .findFirst()
                                .ifPresent(analysis -> {
                                    analysis.setStatus(AnalysisStatus.FAILED);
                                    analysisRepository.save(analysis);
                                });
                    }
                });
        }
}
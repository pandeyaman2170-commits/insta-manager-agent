package com.clipgrowth.clipgrowth_backend.processing;

import com.clipgrowth.clipgrowth_backend.analysis.AnalysisStatus;
import com.clipgrowth.clipgrowth_backend.analysis.VideoAnalysis;
import com.clipgrowth.clipgrowth_backend.analysis.VideoAnalysisRepository;
import com.clipgrowth.clipgrowth_backend.clip.ClipCandidateService;
import com.clipgrowth.clipgrowth_backend.video.Video;
import org.springframework.stereotype.Service;

@Service
public class LocalVideoProcessor implements VideoProcessor {

    private final VideoAnalysisRepository videoAnalysisRepository;
    private final ClipCandidateService clipCandidateService;

    public LocalVideoProcessor(
            VideoAnalysisRepository videoAnalysisRepository,
            ClipCandidateService clipCandidateService) {

        this.videoAnalysisRepository = videoAnalysisRepository;
        this.clipCandidateService = clipCandidateService;
    }

    @Override
    public void process(Video video, ProcessingJob job) {

        job.setStatus(ProcessingJobStatus.PROCESSING);

        VideoAnalysis analysis = new VideoAnalysis();
        analysis.setVideo(video);
        analysis.setStatus(AnalysisStatus.PROCESSING);

        videoAnalysisRepository.save(analysis);

        System.out.println("Processing video: " + video.getId());

        /*
         * Temporary candidate generation.
         *
         * Later this will be replaced by:
         * - transcription
         * - scene detection
         * - semantic analysis
         * - AI clip selection
         */

        clipCandidateService.createCandidate(
                video,
                0,
                30,
                "First Clip Candidate",
                "This could become the opening hook",
                "Generated caption placeholder",
                0.50
        );

        analysis.setStatus(AnalysisStatus.COMPLETED);
        videoAnalysisRepository.save(analysis);

        job.setStatus(ProcessingJobStatus.COMPLETED);
    }
}
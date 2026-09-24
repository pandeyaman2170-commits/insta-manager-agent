package com.clipgrowth.clipgrowth_backend.analysis;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analyses")
public class VideoAnalysisController {

    private final VideoAnalysisRepository videoAnalysisRepository;

    public VideoAnalysisController(VideoAnalysisRepository videoAnalysisRepository) {
        this.videoAnalysisRepository = videoAnalysisRepository;
    }

    @GetMapping
    public List<VideoAnalysis> getAnalyses() {
        return videoAnalysisRepository.findAll();
    }

    @GetMapping("/{id}")
    public VideoAnalysis getAnalysis(@PathVariable Long id) {
        return videoAnalysisRepository.findById(id)
                .orElseThrow();
    }
}
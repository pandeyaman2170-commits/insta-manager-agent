package com.clipgrowth.clipgrowth_backend.analysis;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analysis")
public class VideoAnalysisController {

    private final VideoAnalysisRepository repository;

    public VideoAnalysisController(VideoAnalysisRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<VideoAnalysis> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public VideoAnalysis getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow();
    }
}
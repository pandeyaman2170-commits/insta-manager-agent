package com.clipgrowth.clipgrowth_backend.clip;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clip-candidates")
public class ClipCandidateController {

    private final ClipCandidateService clipCandidateService;

    public ClipCandidateController(
            ClipCandidateService clipCandidateService) {
        this.clipCandidateService = clipCandidateService;
    }

    @GetMapping
    public List<Clip> getCandidates() {
        return clipCandidateService.getCandidates();
    }

    @GetMapping("/video/{videoId}")
    public List<Clip> getCandidatesForVideo(
            @PathVariable Long videoId) {

        return clipCandidateService.getCandidatesForVideo(videoId);
    }
}
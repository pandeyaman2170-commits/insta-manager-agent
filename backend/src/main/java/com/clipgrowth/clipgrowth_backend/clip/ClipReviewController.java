package com.clipgrowth.clipgrowth_backend.clip;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clips")
public class ClipReviewController {

    private final ClipReviewService clipReviewService;

    public ClipReviewController(ClipReviewService clipReviewService) {
        this.clipReviewService = clipReviewService;
    }

    @PostMapping("/{clipId}/approve")
    public Clip approve(@PathVariable Long clipId) {
        return clipReviewService.approve(clipId);
    }

    @PostMapping("/{clipId}/reject")
    public Clip reject(@PathVariable Long clipId) {
        return clipReviewService.reject(clipId);
    }
}
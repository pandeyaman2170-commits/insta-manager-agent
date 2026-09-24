package com.clipgrowth.clipgrowth_backend.clip;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clips")
public class ClipController {

    private final ClipService clipService;

    public ClipController(ClipService clipService) {
        this.clipService = clipService;
    }

    @GetMapping
    public List<Clip> getClips() {
        return clipService.getClips();
    }

    @GetMapping("/video/{videoId}")
    public List<Clip> getClipsForVideo(@PathVariable Long videoId) {
        return clipService.getClipsForVideo(videoId);
    }
    @PostMapping
    public Clip createClip(
            @RequestParam Long videoId,
            @RequestParam Long startTimeSeconds,
            @RequestParam Long endTimeSeconds,
            @RequestParam String title,
            @RequestParam String hook,
            @RequestParam String caption) {

        return clipService.createClip(
                videoId,
                startTimeSeconds,
                endTimeSeconds,
                title,
                hook,
                caption
        );
    }
}
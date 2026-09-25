package com.clipgrowth.clipgrowth_backend.clip;

import com.clipgrowth.clipgrowth_backend.video.Video;
import com.clipgrowth.clipgrowth_backend.video.VideoRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clips/generate")
public class ClipGenerationController {

    private final ClipGenerationService clipGenerationService;
    private final VideoRepository videoRepository;

    public ClipGenerationController(
            ClipGenerationService clipGenerationService,
            VideoRepository videoRepository) {
        this.clipGenerationService = clipGenerationService;
        this.videoRepository = videoRepository;
    }

    @PostMapping("/{videoId}")
    public Clip createCandidate(@PathVariable Long videoId) {

        Video video = videoRepository.findById(videoId)
                .orElseThrow();

        return clipGenerationService.createCandidate(
                video,
                0,
                30,
                "Candidate clip",
                "Generated hook",
                0.0
        );
    }
}
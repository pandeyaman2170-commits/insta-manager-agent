package com.clipgrowth.clipgrowth_backend.rendering;

import com.clipgrowth.clipgrowth_backend.clip.Clip;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clips")
public class ClipRenderingController {

    private final ClipRenderingService clipRenderingService;

    public ClipRenderingController(
            ClipRenderingService clipRenderingService) {

        this.clipRenderingService = clipRenderingService;
    }

    @PostMapping("/{clipId}/render")
    public Clip render(@PathVariable Long clipId) throws Exception {
        return clipRenderingService.render(clipId);
    }
}
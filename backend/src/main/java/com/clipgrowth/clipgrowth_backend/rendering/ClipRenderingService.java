package com.clipgrowth.clipgrowth_backend.rendering;

import com.clipgrowth.clipgrowth_backend.clip.Clip;
import com.clipgrowth.clipgrowth_backend.clip.ClipRepository;
import com.clipgrowth.clipgrowth_backend.clip.ClipStatus;
import org.springframework.stereotype.Service;

@Service
public class ClipRenderingService {

    private final ClipRepository clipRepository;
    private final VideoRenderer videoRenderer;

    public ClipRenderingService(
            ClipRepository clipRepository,
            VideoRenderer videoRenderer) {

        this.clipRepository = clipRepository;
        this.videoRenderer = videoRenderer;
    }

    public Clip render(Long clipId) throws Exception {

        Clip clip = clipRepository.findById(clipId)
                .orElseThrow();

        if (clip.getStatus() != ClipStatus.APPROVED) {
            throw new IllegalStateException(
                    "Only approved clips can be rendered"
            );
        }

        clip.setStatus(ClipStatus.PROCESSING);
        clipRepository.save(clip);

        try {
            String storageKey = videoRenderer.render(clip);

            clip.setStorageKey(storageKey);
            clip.setStatus(ClipStatus.READY);

            return clipRepository.save(clip);

        } catch (Exception e) {

            clip.setStatus(ClipStatus.FAILED);
            clipRepository.save(clip);

            throw e;
        }
    }
}
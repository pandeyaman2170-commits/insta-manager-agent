package com.clipgrowth.clipgrowth_backend.clip;

import org.springframework.stereotype.Service;

@Service
public class ClipReviewService {

    private final ClipRepository clipRepository;

    public ClipReviewService(ClipRepository clipRepository) {
        this.clipRepository = clipRepository;
    }

    public Clip approve(Long clipId) {
        Clip clip = clipRepository.findById(clipId)
                .orElseThrow();

        clip.setStatus(ClipStatus.APPROVED);

        return clipRepository.save(clip);
    }

    public Clip reject(Long clipId) {
        Clip clip = clipRepository.findById(clipId)
                .orElseThrow();

        clip.setStatus(ClipStatus.REJECTED);

        return clipRepository.save(clip);
    }
}
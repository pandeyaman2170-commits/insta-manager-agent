package com.clipgrowth.clipgrowth_backend.clip;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ClipCandidateService {

    private final ClipRepository clipRepository;

    public ClipCandidateService(ClipRepository clipRepository) {
        this.clipRepository = clipRepository;
    }

    public List<Clip> getCandidates() {
        return clipRepository.findAll()
                .stream()
                .filter(clip -> clip.getStatus() == ClipStatus.CANDIDATE)
                .sorted(
                        Comparator.comparing(
                                Clip::getPredictedScore,
                                Comparator.nullsLast(Comparator.reverseOrder())
                        )
                )
                .toList();
    }

    public List<Clip> getCandidatesForVideo(Long videoId) {
        return clipRepository.findAll()
                .stream()
                .filter(clip ->
                        clip.getStatus() == ClipStatus.CANDIDATE
                                && clip.getSourceVideo().getId().equals(videoId))
                .sorted(
                        Comparator.comparing(
                                Clip::getPredictedScore,
                                Comparator.nullsLast(Comparator.reverseOrder())
                        )
                )
                .toList();
    }
}
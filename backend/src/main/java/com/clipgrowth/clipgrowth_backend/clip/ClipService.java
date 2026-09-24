package com.clipgrowth.clipgrowth_backend.clip;

import com.clipgrowth.clipgrowth_backend.video.Video;
import com.clipgrowth.clipgrowth_backend.video.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClipService {

    private final ClipRepository clipRepository;
    private final VideoRepository videoRepository;

    public ClipService(
            ClipRepository clipRepository,
            VideoRepository videoRepository) {
        this.clipRepository = clipRepository;
        this.videoRepository = videoRepository;
    }

    public List<Clip> getClips() {
        return clipRepository.findAll();
    }

    public List<Clip> getClipsForVideo(Long videoId) {
        videoRepository.findById(videoId).orElseThrow();

        return clipRepository.findBySourceVideoId(videoId);
    }

    public Clip createClip(
            Long videoId,
            Long startTimeSeconds,
            Long endTimeSeconds,
            String title,
            String hook,
            String caption) {

        Video video = videoRepository.findById(videoId)
                .orElseThrow();

        Clip clip = new Clip();

        clip.setSourceVideo(video);
        clip.setStartTimeSeconds(startTimeSeconds);
        clip.setEndTimeSeconds(endTimeSeconds);
        clip.setTitle(title);
        clip.setHook(hook);
        clip.setCaption(caption);
        clip.setStatus(ClipStatus.CANDIDATE);

        return clipRepository.save(clip);
    }
}
package com.clipgrowth.clipgrowth_backend.video;

import com.clipgrowth.clipgrowth_backend.storage.StorageService;
import com.clipgrowth.clipgrowth_backend.storage.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.clipgrowth.clipgrowth_backend.processing.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    private final VideoRepository videoRepository;
    private final StorageService storageService;
    private final ProcessingJobService processingJobService;
    
    public VideoController(
            VideoRepository videoRepository,
            StorageService storageService,
            ProcessingJobService processingJobService) {
        this.videoRepository = videoRepository;
        this.storageService = storageService;
        this.processingJobService = processingJobService;
    }

    @GetMapping
    public List<Video> getVideos() {
        return videoRepository.findAll();
    }

    @PostMapping("/upload")
    public ResponseEntity<Video> uploadVideo(
            @RequestParam("file") MultipartFile file) throws IOException {

        String storageKey = UUID.randomUUID() + "-" + file.getOriginalFilename();

        storageService.upload(file, storageKey);

        Video video = new Video();
        video.setOriginalFilename(file.getOriginalFilename());
        video.setStorageKey(storageKey);
        video.setStatus(VideoStatus.UPLOADED);

        Video savedVideo = videoRepository.save(video);
        processingJobService.createVideoProcessingJob(savedVideo);

        return ResponseEntity.ok(savedVideo);
    }
}
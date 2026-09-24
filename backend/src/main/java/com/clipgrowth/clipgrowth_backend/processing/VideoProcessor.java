package com.clipgrowth.clipgrowth_backend.processing;

import com.clipgrowth.clipgrowth_backend.video.Video;

public interface VideoProcessor {

    void process(Video video, ProcessingJob job);
}
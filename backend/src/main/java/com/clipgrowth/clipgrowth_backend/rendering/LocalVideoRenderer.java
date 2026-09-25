package com.clipgrowth.clipgrowth_backend.rendering;

import com.clipgrowth.clipgrowth_backend.clip.Clip;
import com.clipgrowth.clipgrowth_backend.storage.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalVideoRenderer implements VideoRenderer {

    private final StorageService storageService;

    @Value("${storage.local.path:./storage}")
    private String storagePath;

    public LocalVideoRenderer(StorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public String render(Clip clip) throws Exception {

        Path input = Path.of(
                storagePath,
                clip.getSourceVideo().getStorageKey()
        );

        String outputKey = "clips/" + clip.getId() + ".mp4";

        Path output = Path.of(
                storagePath,
                outputKey
        );

        Files.createDirectories(output.getParent());

        long duration =
                clip.getEndTimeSeconds() - clip.getStartTimeSeconds();

        ProcessBuilder processBuilder = new ProcessBuilder(
                "ffmpeg",
                "-y",
                "-ss",
                String.valueOf(clip.getStartTimeSeconds()),
                "-i",
                input.toString(),
                "-t",
                String.valueOf(duration),
                "-c:v",
                "libx264",
                "-c:a",
                "aac",
                output.toString()
        );

        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        int exitCode = process.waitFor();

        if (exitCode != 0) {
            throw new RuntimeException(
                    "FFmpeg failed with exit code: " + exitCode
            );
        }

        return outputKey;
    }
}
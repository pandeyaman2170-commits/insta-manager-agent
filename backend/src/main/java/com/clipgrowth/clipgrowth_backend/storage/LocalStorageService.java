package com.clipgrowth.clipgrowth_backend.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalStorageService implements StorageService {

    private final Path storagePath;

    public LocalStorageService(
            @Value("${storage.local.path:./storage}") String storagePath) {
        this.storagePath = Path.of(storagePath);
    }

    @Override
    public String upload(MultipartFile file, String storageKey) throws IOException {
        Files.createDirectories(storagePath);

        Path target = storagePath.resolve(storageKey);
        Files.createDirectories(target.getParent());

        file.transferTo(target);

        return storageKey;
    }

    @Override
    public void delete(String storageKey) throws IOException {
        Files.deleteIfExists(storagePath.resolve(storageKey));
    }
}
package com.clipgrowth.clipgrowth_backend.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {

    String upload(MultipartFile file, String storageKey) throws IOException;

    void delete(String storageKey) throws IOException;
}
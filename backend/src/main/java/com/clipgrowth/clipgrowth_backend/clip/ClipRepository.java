package com.clipgrowth.clipgrowth_backend.clip;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClipRepository extends JpaRepository<Clip, Long> {

    List<Clip> findBySourceVideoId(Long videoId);

}
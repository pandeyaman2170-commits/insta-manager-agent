package com.clipgrowth.clipgrowth_backend.processing;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessingJobRepository extends JpaRepository<ProcessingJob, Long> {
}
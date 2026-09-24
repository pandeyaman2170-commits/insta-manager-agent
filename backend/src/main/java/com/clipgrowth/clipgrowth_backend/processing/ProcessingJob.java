package com.clipgrowth.clipgrowth_backend.processing;

import com.clipgrowth.clipgrowth_backend.video.Video;
import jakarta.persistence.*;

@Entity
public class ProcessingJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

    @Enumerated(EnumType.STRING)
    private ProcessingJobStatus status;

    private String jobType;

    public Long getId() {
        return id;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public ProcessingJobStatus getStatus() {
        return status;
    }

    public void setStatus(ProcessingJobStatus status) {
        this.status = status;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }
}
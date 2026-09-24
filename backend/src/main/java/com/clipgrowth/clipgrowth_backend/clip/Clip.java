package com.clipgrowth.clipgrowth_backend.clip;

import com.clipgrowth.clipgrowth_backend.video.Video;
import jakarta.persistence.*;

@Entity
public class Clip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Video sourceVideo;

    private Long startTimeSeconds;

    private Long endTimeSeconds;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String hook;

    @Column(columnDefinition = "TEXT")
    private String caption;

    private String storageKey;

    @Enumerated(EnumType.STRING)
    private ClipStatus status;

    private Double predictedScore;

    public Long getId() {
        return id;
    }

    public Video getSourceVideo() {
        return sourceVideo;
    }

    public void setSourceVideo(Video sourceVideo) {
        this.sourceVideo = sourceVideo;
    }

    public Long getStartTimeSeconds() {
        return startTimeSeconds;
    }

    public void setStartTimeSeconds(Long startTimeSeconds) {
        this.startTimeSeconds = startTimeSeconds;
    }

    public Long getEndTimeSeconds() {
        return endTimeSeconds;
    }

    public void setEndTimeSeconds(Long endTimeSeconds) {
        this.endTimeSeconds = endTimeSeconds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHook() {
        return hook;
    }

    public void setHook(String hook) {
        this.hook = hook;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getStorageKey() {
        return storageKey;
    }

    public void setStorageKey(String storageKey) {
        this.storageKey = storageKey;
    }

    public ClipStatus getStatus() {
        return status;
    }

    public void setStatus(ClipStatus status) {
        this.status = status;
    }

    public Double getPredictedScore() {
        return predictedScore;
    }

    public void setPredictedScore(Double predictedScore) {
        this.predictedScore = predictedScore;
    }
}
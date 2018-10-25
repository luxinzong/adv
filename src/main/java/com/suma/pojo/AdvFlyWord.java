package com.suma.pojo;

public class AdvFlyWord {
    private Long id;

    private String content;

    private Long displayTimes;

    private Long intervalTime;

    private String fontColour;

    private String backgroundColour;

    private Long speed;

    private Long direct;

    private Long duration;

    private String fontSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getDisplayTimes() {
        return displayTimes;
    }

    public void setDisplayTimes(Long displayTimes) {
        this.displayTimes = displayTimes;
    }

    public Long getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(Long intervalTime) {
        this.intervalTime = intervalTime;
    }

    public String getFontColour() {
        return fontColour;
    }

    public void setFontColour(String fontColour) {
        this.fontColour = fontColour == null ? null : fontColour.trim();
    }

    public String getBackgroundColour() {
        return backgroundColour;
    }

    public void setBackgroundColour(String backgroundColour) {
        this.backgroundColour = backgroundColour == null ? null : backgroundColour.trim();
    }

    public Long getSpeed() {
        return speed;
    }

    public void setSpeed(Long speed) {
        this.speed = speed;
    }

    public Long getDirect() {
        return direct;
    }

    public void setDirect(Long direct) {
        this.direct = direct;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize == null ? null : fontSize.trim();
    }
}
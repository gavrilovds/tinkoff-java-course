package edu.hw1.task1;

public final class VideoUtils {

    private static final int SECONDS_IN_MINUTE = 60;

    private VideoUtils() {
    }

    public static int minuteToSeconds(String videoLength) {
        if (videoLength == null || !videoLength.matches("(\\d{2,}):([0-5]\\d)")) {
            return -1;
        }
        int minutes = Integer.parseInt(videoLength.split(":")[0]);
        int seconds = Integer.parseInt(videoLength.split(":")[1]);
        return minutes * SECONDS_IN_MINUTE + seconds;
    }

}

package edu.hw1.task1;



public final class VideoUtils {

    private final static int SECONDS_IN_MINUTE = 60;

    private VideoUtils() {
    }

    public static int minuteToSeconds(String videoLength) throws NullPointerException {
        if (videoLength == null || videoLength.isEmpty()) {
            throw new NullPointerException("Empty input");
        }
        String[] parts = videoLength.split(":");
        if (parts.length != 2 || parts[1].length() != 2) {
            return -1;
        }
        try {
            int minutes = Integer.parseInt(parts[0]);
            int seconds = Integer.parseInt(parts[1]);
            if (seconds >= SECONDS_IN_MINUTE || seconds < 0 || minutes < 0) {
                return -1;
            }
            return minutes * SECONDS_IN_MINUTE + seconds;
        } catch (NumberFormatException e) {
            return -1;
        }

    }

}

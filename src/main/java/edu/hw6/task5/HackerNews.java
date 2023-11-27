package edu.hw6.task5;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.net.http.HttpClient.newHttpClient;

public class HackerNews {

    private static final Pattern TITLE_PATTERN = Pattern.compile("\"title\":\"([^\"]+)\"");
    private static final String TOP_STORIES_HOST_NAME = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String NEWS_HOST_NAME = "https://hacker-news.firebaseio.com/v0/item/";

    public long[] getHackerNewsTopStories() {
        try {
            var response = getResponse(TOP_STORIES_HOST_NAME);
            return convertJsonToLongArray(response.body());
        } catch (Exception e) {
            return new long[0];
        }
    }

    public String getNewsTitle(long newsId) {
        try {
            var response = getResponse(NEWS_HOST_NAME + newsId + ".json");
            return getNewsTitleFromJson(response.body());
        } catch (Exception e) {
            return "";
        }
    }

    private HttpResponse<String> getResponse(String stringUri) {
        try (HttpClient client = newHttpClient()) {
            var request =
                HttpRequest.newBuilder().GET().uri(new URI(stringUri))
                    .build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getNewsTitleFromJson(String json) {
        Matcher matcher = TITLE_PATTERN.matcher(json);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    private long[] convertJsonToLongArray(String json) {
        String[] topStories = json.substring(1, json.length() - 1).split(",");
        return Arrays.stream(topStories).mapToLong(Long::parseLong).toArray();
    }
}

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

    public long[] getHackerNewsTopStories() {
        try {
            var request =
                HttpRequest.newBuilder().GET().uri(new URI("https://hacker-news.firebaseio.com/v0/topstories.json"))
                    .build();
            HttpClient client = newHttpClient();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            client.close();
            return convertJsonToLongArray(response.body());
        } catch (Exception e) {
            return new long[0];
        }
    }

    public String getNewsTitle(long newsId) {
        try {
            var request = HttpRequest.newBuilder().GET()
                .uri(new URI("https://hacker-news.firebaseio.com/v0/item/" + newsId + ".json")).build();
            HttpClient client = newHttpClient();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            client.close();
            return getNewsTitleFromJson(response.body());
        } catch (Exception e) {
            return "";
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

package edu.project3;

import edu.project3.model.NginxLog;
import edu.project3.model.Request;
import edu.project3.model.Response;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LogParser {

    private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
    private static final Pattern LOG_PATTERN =
        Pattern.compile("(.*) - (.*) \\[(.*)\\] \"(.*) (.*) (.*)\" (\\d+) (\\d+) \"(.*)\" \"(.*)\"");

    private LogParser() {
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static NginxLog parseLog(String line) {
        Matcher matcher = LOG_PATTERN.matcher(line);
        if (!matcher.find()) {
            throw new IllegalArgumentException("line can not been parsed");
        }
        return NginxLog.builder()
            .remoteAddress(matcher.group(1))
            .remoteUser(matcher.group(2))
            .timeLocal(OffsetDateTime.parse(matcher.group(3), FORMATTER))
            .request(Request.builder()
                .type(matcher.group(4))
                .resource(matcher.group(5))
                .protocolVersion(matcher.group(6))
                .userAgent(matcher.group(10))
                .build())
            .response(Response.builder()
                .statusCode(Integer.parseInt(matcher.group(7)))
                .bodyBytesSend(Integer.parseInt(matcher.group(8)))
                .build())
            .referer(matcher.group(9))
            .build();
    }
}

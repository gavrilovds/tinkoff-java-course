package edu.project3.model;

import lombok.Builder;

@Builder
public record Response(int statusCode, int bodyBytesSend) {

}

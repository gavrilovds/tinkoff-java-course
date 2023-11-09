package edu.project3.model;

import java.util.List;

public record LogSourceWrapper(LogData logData, List<NginxLog> logs) {

}

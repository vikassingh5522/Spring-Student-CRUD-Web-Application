package com.example.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LogViewer {

    private static final Logger logger = LoggerFactory.getLogger(LogViewer.class);
    private static final String LOG_FILE_PATH = System.getProperty("user.dir") + "/logs/student-crud.log";

    /**
     * Reads the entire log file and returns it as a single string.
     */
    public String readLogFile() {
        try {
            Path logDir = Paths.get(System.getProperty("user.dir"), "logs");
            Files.createDirectories(logDir);

            Path logFile = Paths.get(LOG_FILE_PATH);
            logger.debug("Reading log file from: {}", logFile);

            if (Files.exists(logFile)) {
                return Files.lines(logFile).collect(Collectors.joining("\n"));
            } else {
                logger.warn("Log file does not exist: {}", logFile);
                return "Log file not found at: " + LOG_FILE_PATH;
            }
        } catch (Exception e) {
            logger.error("Error reading log file: {}", e.getMessage(), e);
            return "Unable to read logs: " + LOG_FILE_PATH + " - " + e.getMessage();
        }
    }

    /**
     * Returns the last N lines of the log file.
     */
    public String getLastLogLines(int numberOfLines) {
        try {
            Path logFile = Paths.get(LOG_FILE_PATH);

            if (!Files.exists(logFile)) {
                logger.warn("Log file not found for reading last lines.");
                return "Log file not found at: " + LOG_FILE_PATH;
            }

            List<String> allLines = Files.readAllLines(logFile);
            int totalLines = allLines.size();
            List<String> lastLines = allLines.subList(Math.max(0, totalLines - numberOfLines), totalLines);

            return String.join("\n", lastLines);
        } catch (IOException e) {
            logger.error("Failed to get last {} lines from log file: {}", numberOfLines, e.getMessage(), e);
            return "Error retrieving last lines of logs: " + e.getMessage();
        }
    }
}

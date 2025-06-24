package com.example.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Component
public class LogViewer {
    private static final Logger logger = LoggerFactory.getLogger(LogViewer.class);
    private static final String LOG_DIR = "logs";
    private static final String LOG_FILE_PATH = LOG_DIR + "/student-crud.log";

    public String readLogFile() {
        try {
            Path logDir = Path.of(LOG_DIR);
            Path logFile = Path.of(LOG_FILE_PATH);

            // Ensure logs directory exists
            if (!Files.exists(logDir)) {
                Files.createDirectories(logDir);
            }

            // If log file does not exist, create an empty file
            if (!Files.exists(logFile)) {
                Files.createFile(logFile);
                logger.info("Log file created: {}", logFile.toAbsolutePath());
                return "Log file is empty.";
            }

            // Read file content
            String content = Files.lines(logFile).collect(Collectors.joining("\n"));

            // If log file is empty
            if (content.isBlank()) {
                return "Log file is currently empty.";
            }

            return content;

        } catch (IOException e) {
            logger.error("Error reading log file at {}: {}", LOG_FILE_PATH, e.getMessage());
            return "Unable to read logs: " + e.getMessage();
        }
    }
}

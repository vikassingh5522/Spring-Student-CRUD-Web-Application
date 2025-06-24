package com.example.util;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class LogViewer {
    private static final Logger logger = LoggerFactory.getLogger(LogViewer.class);
    private static final String LOG_DIR = "logs";
    private static final String LOG_FILE_PATH = LOG_DIR + "/student-crud.log";

    /**
     * Reads only the last N lines of the log file.
     */
    public List<String> getLastLogLines(int lineCount) {
        List<String> lines = new ArrayList<>();
        File logFile = new File(LOG_FILE_PATH);

        try {
            // Create log directory and file if not present
            Path logDir = Path.of(LOG_DIR);
            if (!Files.exists(logDir)) Files.createDirectories(logDir);
            if (!Files.exists(logFile.toPath())) {
                Files.createFile(logFile.toPath());
                logger.info("Log file created: {}", logFile.getAbsolutePath());
                lines.add("Log file is currently empty.");
                return lines;
            }

            // Use ReversedLinesFileReader to read from bottom up
            try (ReversedLinesFileReader reader = new ReversedLinesFileReader(logFile, StandardCharsets.UTF_8)) {
                for (int i = 0; i < lineCount; i++) {
                    String line = reader.readLine();
                    if (line == null) break;
                    lines.add(0, line); // Maintain correct order
                }
            }
        } catch (IOException e) {
            logger.error("Error reading log file at {}: {}", LOG_FILE_PATH, e.getMessage());
            lines.clear();
            lines.add("Unable to read logs: " + e.getMessage());
        }

        return lines;
    }
}

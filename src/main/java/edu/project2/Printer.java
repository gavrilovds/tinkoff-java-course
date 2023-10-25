package edu.project2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Printer {

    private static final Logger LOGGER = LogManager.getLogger();

    public void print(String line) {
        LOGGER.info(line);
    }

}

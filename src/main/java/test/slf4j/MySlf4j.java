package test.slf4j;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

public class MySlf4j {
    public static void main(String[] args) {
        Logger logger= LoggerFactory.getLogger(MySlf4j.class);
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }
}

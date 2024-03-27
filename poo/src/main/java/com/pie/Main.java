package com.pie;

import com.pie.utils.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    // I used this class for testing the in and out methods
    private static final Logger log = LogManager.getLogger();
    public static final String FILE_ADDRESS = "poo/src/main/resources/test.txt";

    public static void main(String[] args) {
        writeTest();
        writeTest();
        log.info(Main::readTest);

        System.out.println("Hello");
    }

    private static void cleanTest() {
        FileUtils.clean(FILE_ADDRESS );
    }

    private static String readTest() {
        return FileUtils.read(FILE_ADDRESS);
    }

    private static void writeTest() {
        FileUtils.write(FILE_ADDRESS, " message, ");
    }


}
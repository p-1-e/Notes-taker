package com.pie.notes.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {
    private final String fileAddressTest = "src/test/resources/test.txt";
    @Test
    void newFile() {
    }

    @Test
    void clean() {
    }

    @Test
    void write() {
        FileUtils.write(fileAddressTest, "hello");
    }

    @Test
    void read() {
    }
}
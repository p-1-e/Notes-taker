package com.pie.utils;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {
    @Test
    public void newFileTest(){
        String fileName = "test.txt";
        String result = FileUtils.newFile(fileName);
        assertNotNull(result);
        assertEquals(fileName, result);
        assertTrue(Files.exists(Path.of(fileName)));
    }

    @Test
    public void readTest(){
        String fileAddress = STR."\{NoteConstants.DIRECTORY}testing.txt";
        String result = FileUtils.read(fileAddress);
        String target = "this file is for testing";
        assertEquals(result, target);
    }
    @Test
    public void writeTest(){

    }

    @Test
    public void cleanFileTest(){
        String fileAddress = "testing.txt";
        var file = FileUtils.clean(fileAddress);
        assertNotNull(file);
    }
}
package com.pie.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {
    public static final String FILE_ADDRESS = "poo/src/main/resources/test.txt";

    @Test
    public void newFileTest(){
        String fileString = FileUtils.newFile(FILE_ADDRESS);
        assertNotNull(fileString);
    }

    @Test
    public void readTest(){

    }
    @Test
    public void writeTest(){
        FileUtils.write(FILE_ADDRESS , "message");
    }

    @Test
    public void cleanFileTest(){
        String fileAddress = "testing.txt";
        var file = FileUtils.clean(fileAddress);
        assertNotNull(file);
    }
}
package com.pie;

import com.pie.utils.FileUtils;
import com.pie.utils.NoteConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger log = LogManager.getLogger();
    public static void main(String[] args) {
        String fileAddress = "poo/src/main/resources/archive.txt";
        String file = FileUtils.newFile(fileAddress);
        log.info(STR."the file is \{file}");
    }
}
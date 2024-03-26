package com.pie.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    private static final Logger log = LogManager.getLogger();

    private FileUtils() {
    } // avoids than someone initialize the class

    public static String newFile(String fileAddress) {
        try {
            File file = new File(fileAddress);
            if (file.createNewFile()) {
                log.info(STR."file created in \{fileAddress}");
                return fileAddress;
            } else {
                log.error("can't create the file");
                return null;
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }

    }

    public static String clean(String fileAddress) { // this method delete all the text in the file
        try {
            new FileWriter(fileAddress, false).close();
            return fileAddress;
        } catch (IOException e) {
            return null;
        }
    }

    public static void write(String fileAddress, String noteString) {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(fileAddress), StandardCharsets.UTF_8)) {
            writer.write(noteString); // write the new text in the file
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String read(String fileAddress) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileAddress))) {
            return br.readLine();
        } catch (FileNotFoundException e) {
            log.error("the file was not found");
        } catch (IOException e) {
            log.error("xd i don't not what happened but this crashes");
        }
        return "null";
    }

}

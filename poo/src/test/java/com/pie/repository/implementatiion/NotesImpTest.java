package com.pie.repository.implementatiion;

import com.pie.repository.NotesInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesImpTest {

    public static final Logger log = LogManager.getLogger();

    @Test
    void read() {
        NotesInterface noteImpl = new NotesImp();
        log.info(STR."content: \n \{noteImpl.read()}");
    }

    @Test
    void add() {
    }

    @Test
    void write() {
    }

    @Test
    void findAll() {
    }
}
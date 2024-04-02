package com.pie.interfaces;

import com.pie.data.Note;
import com.pie.utils.FileUtils;
import com.pie.utils.NoteUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.pie.utils.NoteConstants.NOTE_ARRAY;
import static com.pie.utils.NoteConstants.SEPARATOR;

public class NotesImp implements NotesInterface {
    private static final Logger log = LogManager.getLogger();

    private static List<Note> notes = new ArrayList<>();

    @Override
    public String Read() {
        String note = FileUtils.read(NOTE_ARRAY);
        if (note.isEmpty()) {
            log.info("No existen notas");
        } else {

            Path path = Paths.get(note);
            try {
                Stream<String> stream = Files.lines(path);
                return stream.map(this::build).toList().toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return note;
    }

    @Override
    public List<Note> SearchTitle(String title) {
        List<Note> foundNotes = new ArrayList<>(); // Creamos un arrayLists para almacenar las notas encontradas
        List<Note> allNotes = this.findAll(); // Almacenamos en AllNotes todas las notas que encontramos con el
                                              // this.findAll()

        for (Note note : allNotes) { // Usamos el forEach para recorrer el arrayList de las Notas creadas en el
                                     // sistema
            if (note.getTitle().equalsIgnoreCase(title)) {// El If es para encontrar las similitudes en los titulos de
                                                          // las notas
                                                          // equalsIgnoreCase: Se usa para ignorar mayusculas y
                                                          // minusculas en la busqueda
                foundNotes.add(note); // Agregamos las notas con el titulo que buscamos en el arrayList foundNotes
            }
        }
        return foundNotes; // Retornamos el arrayList con los resultados donde el titulo coincide con el
                           // que buscamos
    }

    @Override
    public List<Note> searchByText(String text) {
        List<Note> allNotes = this.findAll();
        return allNotes.stream()
                .filter(note -> note.getText().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList()); // Filtrar las notas por texto de búsqueda y devolver la lista resultante
    }

    @Override
    public List<Note> Add(Note note) {
        // Actualiza el archivo
        String files = FileUtils.read(NOTE_ARRAY) + SEPARATOR + note.getFileAddress();
        FileUtils.clean(NOTE_ARRAY);
        FileUtils.write(NOTE_ARRAY, files);

        // Actualiza en ram
        notes = findAll();
        notes.add(note);
        return notes;
    }

    @Override
    public Optional<Note> Write(Note note) {
        String content = NoteUtils.noteToText(note);
        FileUtils.write(note.getFileAddress(), content);
        return Optional.of(note);
    }

    @Override
    public List<Note> findAll() {
        notes = new ArrayList<>();
        if (FileUtils.read(NOTE_ARRAY) == null) {
            return notes;
        }
        String[] directions = Objects.requireNonNull(FileUtils.read(NOTE_ARRAY)).split(SEPARATOR);
        for (String diretion : directions) {
            notes.add(toNote(diretion));
        }
        return notes;
    }

    private Note toNote(String direccion) {
        String[] noteString = FileUtils.read(direccion).split(SEPARATOR);
        return new Note(noteString[0], noteString[1], noteString[2], noteString[3]);
    }

    private Note build(String text) {
        String[] noteArray = text.split("#");
        return new Note(noteArray[0], noteArray[1], noteArray[2], noteArray[3]);
    }
}

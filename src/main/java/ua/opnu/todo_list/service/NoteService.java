package ua.opnu.todo_list.service;

import ua.opnu.todo_list.entity.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.opnu.todo_list.repository.NoteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    // Отримати всі нотатки
    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    // Додати нотатку
    public Note add(Note note) {
        return noteRepository.save(note);
    }

    // Видалити нотатку
    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }

    // Оновити нотатку
    public void update(Note note) {
        if (noteRepository.existsById(note.getId())) {
            noteRepository.save(note);
        } else {
            throw new IllegalArgumentException("Note with id " + note.getId() + " not found");
        }
    }

    // Отримати одну нотатку
    public Note getById(long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Note not found: " + id));
    }
}
package ua.opnu.todo_list.service;

import org.springframework.stereotype.Service;
import ua.opnu.todo_list.model.Note;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NoteServiceImpl implements NoteService {
    private final Map<Long, Note> notes = new ConcurrentHashMap<>();
    private final Random random = new Random();

    @Override
    public List<Note> listAll() {
        return new ArrayList<>(notes.values());
    }

    @Override
    public void add(Note note) {
        long id = Math.abs(random.nextLong(1000));
        note.setId(id);
        notes.put(id, note);
    }

    @Override
    public void deleteById(long id) {
        notes.remove(id);
    }

    @Override
    public void update(Note note) {
        if (notes.containsKey(note.getId())) {
            notes.put(note.getId(), note);
        }
    }

    @Override
    public Note getById(long id) {
        return notes.get(id);
    }
}
package ua.opnu.todo_list;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NoteService {

    private final Map<Long, Note> storage = new ConcurrentHashMap<>();
    private final SecureRandom random = new SecureRandom();

    public List<Note> listAll() {
        return new ArrayList<>(storage.values());
    }

    public Note add(Note note) {
        long id = generateUniqueId();
        note.setId(id);
        storage.put(id, note);
        return note;
    }

    public void deleteById(long id) {
        Note removed = storage.remove(id);
        if (removed == null) {
            throw new NoSuchElementException("Note with id=" + id + " not found");
        }
    }

    public void update(Note note) {
        long id = note.getId();
        Note existing = storage.get(id);
        if (existing == null) {
            throw new NoSuchElementException("Note with id=" + id + " not found");
        }
        existing.setTitle(note.getTitle());
        existing.setContent(note.getContent());
    }

    public Note getById(long id) {
        Note note = storage.get(id);
        if (note == null) {
            throw new NoSuchElementException("Note with id=" + id + " not found");
        }
        return note;
    }

    private long generateUniqueId() {
        long id;
        do {
            id = Math.abs(random.nextLong());
        } while (id == 0L || storage.containsKey(id));
        return id;
    }
}
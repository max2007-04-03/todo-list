package ua.opnu.todo_list; // Обов'язково

import org.springframework.stereotype.Service; // Імпорт для @Service
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random; // Імпорт для Random
import java.util.concurrent.ConcurrentHashMap;

@Service // Позначає клас як сервіс для Spring
public class NoteService {
    private final Map<Long, Note> notes = new ConcurrentHashMap<>();
    private final Random random = new Random();

    public List<Note> listAll() {
        return new ArrayList<>(notes.values());
    }

    public void add(Note note) {
        // Використовуємо setId, який згенерує Lombok у класі Note
        long id = Math.abs(random.nextLong(1000));
        note.setId(id);
        notes.put(id, note);
    }

    public void deleteById(long id) {
        notes.remove(id);
    }

    public void update(Note note) {
        // Використовуємо getId з Lombok
        if (notes.containsKey(note.getId())) {
            notes.put(note.getId(), note);
        }
    }

    public Note getById(long id) {
        return notes.get(id);
    }
}
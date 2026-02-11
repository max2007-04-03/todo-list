package ua.opnu.todo_list.conroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.opnu.todo_list.service.NoteService;

@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list")
    public ModelAndView listNotes() {
        ModelAndView result = new ModelAndView("note-list");
        result.addObject("notes", noteService.listAll());
        return result;
    }
}
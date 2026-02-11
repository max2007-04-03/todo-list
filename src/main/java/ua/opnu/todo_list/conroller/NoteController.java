package ua.opnu.todo_list.conroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.opnu.todo_list.service.NoteService;
import ua.opnu.todo_list.entity.Note;

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

    @PostMapping("/delete")
    public String deleteNote(@RequestParam long id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public ModelAndView editPage(@RequestParam long id) {
        ModelAndView result = new ModelAndView("note-edit");
        result.addObject("note", noteService.getById(id));
        return result;
    }

    @PostMapping("/edit")
    public String saveNote(@ModelAttribute Note note) {
        noteService.update(note);
        return "redirect:/note/list";
    }
}

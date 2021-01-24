package pl.lehmann.notebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.lehmann.notebook.model.AuthorDto;
import pl.lehmann.notebook.model.Note;
import pl.lehmann.notebook.model.NoteDto;
import pl.lehmann.notebook.service.NoteService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class NoteController {

    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @RequestMapping
    public String getAllNotes(Model model) {
        List<Note> list = noteService.getAllNotes();

        model.addAttribute("notes", list);
        return "list-notes";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editNoteById(Model model, @PathVariable("id") Optional<Long> id) {
        if (id.isPresent()) {
            Note entity = noteService.getNoteById(id.get());
            NoteDto noteDto = NoteDto.fromNote(entity);
            model.addAttribute("noteDto", noteDto);
        } else {
            model.addAttribute("noteDto", new NoteDto());
        }
        return "add-edit-note";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteNote(Model model, @PathVariable("id") Long id) {
        model.addAttribute("id", id);
        model.addAttribute("authorDto", new AuthorDto());
        return "delete-note";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.POST)
    public String deleteNoteById(@ModelAttribute AuthorDto authorDto, Model model, @PathVariable("id") Long id) {
        try {
            noteService.deleteNoteById(id, authorDto);
            return "redirect:/";
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            model.addAttribute("message", e.getMessage());
            return "delete-note";
        }
    }

    @RequestMapping(path = "/createNote", method = RequestMethod.POST)
    public String createOrUpdateNote(@ModelAttribute NoteDto noteDto, Model model) {
        try {
            noteService.createOrUpdateNote(noteDto);
            return "redirect:/";
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            model.addAttribute("messageAdd", e.getMessage());
            return "add-edit-note";
        }
    }
}

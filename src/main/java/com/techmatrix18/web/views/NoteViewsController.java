package com.techmatrix18.web.views;

import com.techmatrix18.model.Note;
import com.techmatrix18.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class NoteViewsController {

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/notes")
    public String allNotes(Model model) {
        List<Note> notes = noteRepository.findAll();
        model.addAttribute("notes", notes);

        return "notes.index";
    }

    @PostMapping("/note-add")
    public String addNote(String title, String note) {
        Note newNote = new Note();
        newNote.setTitle(title);
        newNote.setNote(note);

        noteRepository.save(newNote);

        return "redirect:notes";
    }
}

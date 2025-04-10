package com.enotes.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enotes.entity.Note;
import com.enotes.entity.User;
import com.enotes.repository.NoteRepository;
import com.enotes.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private NoteRepository noteRepo;

    // Runs before every handler in this controller to add the user object
    @ModelAttribute
    public void getUser(Principal p, Model m) {
        String email = p.getName();
        User user = userRepo.findByEmail(email);
        m.addAttribute("user", user);
    }

//    @GetMapping("/addNotes")
//    public String addNotes() {
//        return "add_notes";
//    }
    
    @GetMapping("/addNotes")
    public String addNotes(Model model) {
        model.addAttribute("note", new Note());
        return "add_notes";
    }

//    @GetMapping("/viewNotes")
//    public String viewNotes() {
//        return "view_notes";
//    }
    
    @GetMapping("/viewNotes")
    public String viewNotes(Model model, Principal principal) {
        String email = principal.getName();
        User user = userRepo.findByEmail(email);
        List<Note> notes = noteRepo.findByUser(user);
        model.addAttribute("notes", notes);
        return "view_notes";
    }


//    @GetMapping("/editNotes")
//    public String editNotes() {
//        return "edit_notes";
//    }
    
    @GetMapping("/editNotes/{id}")
    public String editNote(@PathVariable Long id, Model model, Principal principal) {
        Note note = noteRepo.findById(id).orElse(null);

        if (note == null || !note.getUser().getEmail().equals(principal.getName())) {
            return "redirect:/user/viewNotes";
        }

        model.addAttribute("note", note);
        return "edit_notes"; // new HTML page we will create
    }


//    @PostMapping("/addNotes")  // correct URL mapping
//    public String saveNotes(@ModelAttribute Note note, Principal principal, HttpSession session) {
//        // get current user by email
//        String email = principal.getName();
//        User user = userRepo.findByEmail(email);
//
//        // associate user with the note
//        note.setUser(user);
//
//        // save the note
//        noteRepo.save(note);
//
//        session.setAttribute("msg", "Note added successfully!");
//
//        return "redirect:/user/addNotes";
//    }
    
    @PostMapping("/addNotes")
    public String saveNotes(@ModelAttribute Note note, Principal principal, HttpSession session) {
        String email = principal.getName();
        User user = userRepo.findByEmail(email);

        note.setUser(user);
        note.setDate(LocalDate.now()); // <-- always set date here

        noteRepo.save(note);

        session.setAttribute("msg", "Note added successfully!");

        return "redirect:/user/addNotes";
    }
    
    @PostMapping("/updateNotes")
    public String updateNote(@ModelAttribute Note note, Principal principal, HttpSession session) {
        String email = principal.getName();
        User user = userRepo.findByEmail(email);

        note.setUser(user); // re-assign user to avoid foreign key issues
        note.setDate(LocalDate.now());
        noteRepo.save(note);

        session.setAttribute("msg", "Note updated successfully!");
        return "redirect:/user/viewNotes";
    }

    @GetMapping("/deleteNotes/{id}")
    public String deleteNotes(@PathVariable("id") int id, HttpSession session) {
        Note note = noteRepo.findById((long) id).orElse(null);
        
        if (note != null) {
            noteRepo.delete(note);
            session.setAttribute("msg", "Note deleted successfully.");
        } else {
            session.setAttribute("msg", "Note not found.");
        }

        return "redirect:/user/viewNotes";
    }

}

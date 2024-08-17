package controller;

import DAO.bookDAO;
import DAO.personDAO;
import Model.person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/person")
public class personController {
    private personDAO personOperations;
    private bookDAO bookOperations;

    @Autowired
    public void setPersonOperations(personDAO personOperations) {
        this.personOperations = personOperations;
    }

    @Autowired
    public void setBookOperations(bookDAO bookOperations) {
        this.bookOperations = bookOperations;
    }

    //ELEMENTS
    @GetMapping
    public String allPerson(Model model){
        model.addAttribute("AllPeoples",personOperations.getAllPerson());
        return "person/mainPage";
    }
    @GetMapping("/{id}")
    public String concretPerson(@PathVariable("id")int id,
                                Model model){
        model.addAttribute("concretPerson",personOperations.getConcretPerson(id));
        model.addAttribute("allPersonBooks",bookOperations.getAllBooksWhereId(id));
        return "person/concretPerson";//Сделать
    }
    //ADD
    @GetMapping("/add")
    public String addPage(@ModelAttribute("newPerson") person person){
        return "person/newPerson"; //Сделать
    }
    @PostMapping
    public String addInDB(@ModelAttribute("newPerson") @Valid person person,
                          BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "person/newPerson";
        personOperations.addPerson(person);
        return "redirect:/person";
    }
    //EDIT
    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id") int id,
                           Model model){
        model.addAttribute("personToEdit",personOperations.getConcretPerson(id));
        return "person/editPage";
    }
    @PatchMapping("/{id}")
    public String editInDB(@PathVariable("id")int id,
                           @ModelAttribute("personToEdit") @Valid person person,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "person/editPage";
        personOperations.editPerson(id,person);
        return "redirect:/person";
    }
    //DELETE
    @DeleteMapping("/{id}")
    public String deleteInDB(@PathVariable("id")int id){
        personOperations.deleteInDB(id);
        return "redirect:/person";
    }
}

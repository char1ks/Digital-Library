package controller;

import DAO.*;
import Model.book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class bookController {
    private bookDAO bookOperations;
    private personDAO personOperations;
    @Autowired
    public void setOperations(bookDAO operations) {
        this.bookOperations = operations;
    }
    @Autowired
    public void setPersonOperations(personDAO personOperations) {
        this.personOperations = personOperations;
    }
    //ELEMENTS
    @GetMapping
    public String allBooks(Model model){
        model.addAttribute("AllBooks",bookOperations.getAllBooks());
        return "books/mainPage";
    }
    @GetMapping("/{id}")
    public String concretBook(@PathVariable("id")int id,
                              Model model){
        model.addAttribute("ConcretBook",bookOperations.getConcretBook(id));
        model.addAttribute("AllPeoples",personOperations.getAllPerson());
        return "books/concretBook";
    }
    //ADD
    @GetMapping("/add")
    public String addPage(@ModelAttribute("newBook") book book){
        return "books/newBook";
    }
    @PostMapping
    public String addInDB(@ModelAttribute("newBook") @Valid book book,
                          BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "books/newBook";
        }
        bookOperations.addBook(book);
        return "redirect:/books";
    }
    //EDIT
    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id")int id,
                           Model model){
        model.addAttribute("editBook",bookOperations.getConcretBook(id));
        return "books/editPage";
    }
    @PatchMapping("/{id}")
    public String editInDB(@PathVariable("id")int id,
                           @ModelAttribute("editBook") @Valid book book,
                           BindingResult bindingResult ){
        if(bindingResult.hasErrors())
            return "books/editPage";
        bookOperations.editInDB(id,book);
        return "redirect:/books";
    }
    //DELETE
    @DeleteMapping("/{id}")
    public String deleteInDB(@PathVariable("id")int id){
        bookOperations.delete(id);
        return "redirect:/books";
    }
    //INSERT PERSON ID
    @PostMapping("/{id}/add")
    public String addBookToPerson(@RequestParam("personId") int personId, @PathVariable int id) {
        bookOperations.setPersonIdTo(personId,id);
        return "redirect:/person";
    }
    //DELETE PERSON ID
    @PostMapping("/{id}/return")
    public String returnBook(@PathVariable("id") int bookId){
        int personId=bookOperations.getConcretBook(bookId).getPersonId();
        bookOperations.setPerconIdNULL(bookId);
        return "redirect:/person/"+personId;
    }
}

package phamvolinhnhi.week3.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import phamvolinhnhi.week3.entity.Book;
import phamvolinhnhi.week3.repository.IBookRepository;
import phamvolinhnhi.week3.services.BookService;
import phamvolinhnhi.week3.services.CategoryService;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.repository.util.ClassUtils.ifPresent;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showAllBooks(Model model){
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }
    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "book/add";
    }
    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book,  BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/add";
        }
        else {
            bookService.addBook(book);
            return "redirect:/books";
        }
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model) {
        Book editBook = bookService.getBookById(id);
        if(editBook == null){
            return "not-found";
        }
        else{
            model.addAttribute("book", editBook);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        }

    }
    @PostMapping("/edit")
    public String editBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());

            return "book/edit";
        }
        else {
            bookService.updateBook(book);
            return "redirect:/books";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        Book book = bookService.getBookById(id);
        bookService.deleteBook(id);
        return "redirect:/books";
    }

}

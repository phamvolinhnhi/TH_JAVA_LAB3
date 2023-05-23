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
        model.addAttribute("categories", categoryService.getAllCategorys());
        return "book/add";
    }
    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book,  BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategorys());
            return "book/add";
        }
        else {
            bookService.addBook(book);
            return "redirect:/books";
        }
    }

//    @GetMapping("/edit/{id}")
//    public String editBookForm(@PathVariable("id") Long id, Model model) {
////        Book editBook = bookService.getBookById(id);
//        Optional<Book> editBook = Optional.ofNullable(bookService.getBookById(id));
//        if(editBook.isPresent()){
//            model.addAttribute("book", editBook);
//            model.addAttribute("categories", categoryService.getAllCategorys());
//            return "book/edit";
//        }
//        else{
//            return "not-found";
//        }
//    }
//    @PostMapping("/edit/{id}")
//    public String editBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
//        if(result.hasErrors()){
//            model.addAttribute("categories", categoryService.getAllCategorys());
//            return "book/edit/" + book.getId();
//        }
//        else {
//            Book editBook = bookService.getBookById(book.getId());
//            bookService.updateBook(editBook);
//            return "redirect:/books";
//        }
//    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model) {
//        List<Book> books = bookService.getAllBooks();
//        Optional<Book> editBook = books.stream()
//                .filter(book -> book.getId().equals(id))
//                .findFirst();
//        if(editBook.isPresent()) {
//            model.addAttribute("book", editBook.get());
//            model.addAttribute("categories", categoryService.getAllCategorys());
//            return "book/edit";
//        }
//        else {
//            return "not-found";
//        }
        Book editBook = bookService.getBookById(id);
        if(editBook == null){
            return "not-found";
        }
        else{
            model.addAttribute("book", bookService.getBookById(id));
            model.addAttribute("categories", categoryService.getAllCategorys());
            return "book/edit";
        }

    }
    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, @Valid @ModelAttribute("book") Book book, BindingResult result,Model model) {
//        List<Book> books = bookService.getAllBooks();
//        books.stream()
//                .filter(b -> b.getId() == book.getId())
//                .findFirst()
//                .ifPresent(b -> books.set(books.indexOf(b), book));
//        return "redirect:/books";
        if(result.hasErrors()) {
            return "book/edit";
        }
        else {
            bookService.updateBook(id, book);
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

package com.example.library.controllers;

import com.example.library.models.Book;
import com.example.library.models.User;
import com.example.library.service.BookService;
import com.example.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
       return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id) {
        return this.bookService.findById(id);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return this.bookService.save(book);
    }

    @PutMapping
    public Book updateBook(@PathVariable long id, @RequestBody Book book) {
         String currentId = null;

         if(currentId.equals(book.getId())) {
             book.setId(id);
         }
         return this.bookService.save(book);

    }

    public void deleteBook(@PathVariable Long id) {
        this.bookService.deleteById(id);
    }

    @PostMapping("/{bookId}/borrow/{userId}")
    public ResponseEntity<Book> borrowBook(@PathVariable long bookId, @PathVariable long userId) {
        Book borrowedBook  = bookService.borrowBook(bookId, userId);
        if(borrowedBook != null) {
            return ResponseEntity.ok(borrowedBook);
        } else {
            return ResponseEntity.badRequest().build();
        }


    }

    @PostMapping("/{bookId}/return")
    public ResponseEntity<Book> returnBook(@PathVariable Long bookId) {
        Book returnedBook = bookService.returnBook(bookId);

        if(returnedBook != null) {
            return ResponseEntity.ok(returnedBook);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


}

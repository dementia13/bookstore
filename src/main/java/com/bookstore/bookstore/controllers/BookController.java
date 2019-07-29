package com.bookstore.bookstore.controllers;

import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.models.Genre;
import com.bookstore.bookstore.repositories.BookRepository;
import com.bookstore.bookstore.services.BookService;
import com.bookstore.bookstore.services.GenreService;
import com.bookstore.bookstore.repositories.CategoryRepository;
import com.bookstore.bookstore.repositories.GenreRepository;
import com.bookstore.bookstore.services.BookService;
import com.bookstore.bookstore.services.CategoryService;
import com.bookstore.bookstore.services.GenreService;
import com.bookstore.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Controller
@RequestMapping("/book")
public class BookController {


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBook(Model model) {
        Set<String> genreList = new TreeSet<>();
        genreList.addAll(bookService.findDistinctGenreBy());
        genreList.add("Thriller");
        genreList.add("Textbook");
        genreList.add("Fantasy");
        genreList.add("History");
        genreList.add("Science Fiction");
        genreList.add("Biography");

        model.addAttribute("genreList", genreList);
        Book book = new Book();
        model.addAttribute("book", book);
        return "addBook";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBookPost(@ModelAttribute("book") Book book, HttpServletRequest request) {
        bookRepository.save(book);

        MultipartFile bookImage = book.getBookImage();

        try {
            byte[] bytes = bookImage.getBytes();
            String name = book.getId() + ".png";
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(new File("src/main/resources/static/img/books/" + name)));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "addBook";
    }

 
    @RequestMapping("/genreBrowser")
    public String showGenres(Model model){
        List<Genre> genres = genreService.listGenres();
        System.out.println(genres);
        model.addAttribute("genres", genres);
        return "genreBrowser";
    }

    @RequestMapping("/searchTitle")
    public List<Book> searchTitle(@RequestParam("title") String title){
        return null;
    }
    @RequestMapping("/genre/{genreId}")
    public String listByGenre(@PathVariable(value = "genreId")long genreId, Model model){

        Genre genre = new Genre();
        genre.setID(genreId);
        List<Book> books = bookRepository.findAllById(genreId);
        System.out.println(books);
        model.addAttribute("bookList", books);
        return "bookshelf";
    }
}

        Genre genre = new Genre();
 //       genre.setID(genreId);
   //     List<Book> books = bookRepository.findAllById(genreId);
     //   System.out.println(books);
       // model.addAttribute("bookList", books);
     //   return "bookshelf";
    }
//}

//
//    private BookRepository bookRepository;
//
//    @Autowired
//    BookController(BookRepository bookRepository){
//        this.bookRepository=bookRepository;
//    }
//
//
//    // controller to import books from postman
//
//    @PostMapping("/importBooks")
//    public void savingBooks(@RequestParam(value = "title") String title,
//                              @RequestParam(value = "author") String author,
//                              @RequestParam(value = "publisher") String publisher,
//                              @RequestParam(value = "publicationDate") String publicationDate,
//                              @RequestParam(value = "language") String language,
//                              @RequestParam(value = "category") String category,
//                              @RequestParam(value = "numberOfPages") int numberOfPages,
//                              @RequestParam(value = "format") String format,
//                              @RequestParam(value = "isbn") int isbn,
//                              @RequestParam(value = "shippingWeight") double shippingWeight,
//                              @RequestParam(value = "listPrice") double listPrice,
//                              @RequestParam(value = "ourPrice") double ourPrice,
//                              @RequestParam(value = "description") String description,
//                              @RequestParam(value = "inStockNumber") int inStockNumber
//                              ){
//
//        Book book=new Book(title,author,publisher,publicationDate,language,category,
//                numberOfPages,format,isbn,shippingWeight,listPrice,ourPrice,
//                description,inStockNumber);
//
//        bookRepository.save(book);
//
//    }


//}
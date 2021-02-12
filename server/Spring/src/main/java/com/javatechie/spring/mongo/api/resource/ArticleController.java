package com.javatechie.spring.mongo.api.resource;

import java.util.List;

import com.javatechie.spring.mongo.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.javatechie.spring.mongo.api.model.Article;
import com.javatechie.spring.mongo.api.repository.ArticleRepo;

@RestController
@RequestMapping("/article")
public class ArticleController {

	private final BookService bookService;

	public ArticleController(BookService bookService) {
		this.bookService = bookService;
	}

	@Autowired
	private ArticleRepo repository;

	@GetMapping("/all")
	public ResponseEntity<List<Article>> getAllBook () {
		List<Article> articles = bookService.findAllBooks();
		return new ResponseEntity<>(articles, HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Article> getBookById (@PathVariable("id") String id) {
		Article article = bookService.findBookById(id);
		return new ResponseEntity<>(article, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Article> addBook(@RequestBody Article article) {
		Article newArticle = bookService.addBook(article);
		return new ResponseEntity<>(newArticle, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Article> updateBook(@RequestBody Article article) {
		Article updateArticle = bookService.updateBook(article);
		return new ResponseEntity<>(updateArticle, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable("id") String id) {
		bookService.deleteBook(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}


}

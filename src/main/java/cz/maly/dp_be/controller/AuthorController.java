package cz.maly.dp_be.controller;

import cz.maly.dp_be.exception.ResourceNotFoundException;
import cz.maly.dp_be.model.Author;
import cz.maly.dp_be.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @CrossOrigin
    @GetMapping("/author")
    public List<Author> getAllAuthors(HttpServletResponse response) {
        List<Author> authors = authorRepository.findAll();
        response.setHeader("Content-Range", String.valueOf(authors.size()));
        response.setHeader("Access-Control-Expose-Headers", "Content-Range");
        return authors;
    }

    @CrossOrigin
    @PostMapping("/author")
    public Author createAuthor(@Valid @RequestBody Author author) {
        return authorRepository.save(author);
    }

    @CrossOrigin
    @GetMapping("/author/{id}")
    public Author getAuthorById(@PathVariable(value = "id") Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", authorId));
    }

    @CrossOrigin
    @PutMapping("/author/{id}")
    public Author updateAuthor(@PathVariable(value = "id") Long authorId,
                                           @Valid @RequestBody Author authorDetails) {

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", authorId));

        author.setName(authorDetails.getName());
        author.setContact(authorDetails.getContact());

        Author updatedAuthor = authorRepository.save(author);
        return updatedAuthor;
    }

    @CrossOrigin
    @DeleteMapping("/author/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable(value = "id") Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", authorId));

        authorRepository.delete(author);

        return ResponseEntity.ok().build();
    }

}

package org.example.demo.controller;
import org.example.demo.entity.Notebook;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import jakarta.validation.Valid;
import java.util.List;
@RestController @RequestMapping("/api/notebooks") public class RestNotebookController {
 final NotebookService service;
 public RestNotebookController(NotebookService service){this.service=service;}
 @GetMapping public List<Notebook> all(){return service.notebooks.catalogue();}
 @GetMapping("/{id}") public Notebook get(@PathVariable Integer id){return service.find(id);}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Notebook create(@Valid @RequestBody NotebookForm form){return service.save(null,form);}
 @PutMapping("/{id}") public Notebook update(@PathVariable Integer id,@Valid @RequestBody NotebookForm form){return service.save(id,form);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Integer id){service.delete(id);}
}

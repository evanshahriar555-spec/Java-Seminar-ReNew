package org.example.demo.controller;
import org.example.demo.repository.*;
import org.example.demo.entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.*;
@Controller public class Controller1 {
 final NotebookService service; final ContactMessageRepository messages;
 public Controller1(NotebookService service,ContactMessageRepository messages){this.service=service;this.messages=messages;}
 @GetMapping("/") public String home(Model m){m.addAttribute("count",service.notebooks.count());return "home";}
 @GetMapping("/database") public String database(@RequestParam(defaultValue="") String q,Model m){String term=q.toLowerCase(Locale.ROOT);m.addAttribute("notebooks",service.notebooks.catalogue().stream().filter(n->(n.getManufacturer()+" "+n.getType()+" "+n.getProcessor().getType()+" "+n.getOpsystem().getOsname()).toLowerCase(Locale.ROOT).contains(term)).toList());m.addAttribute("q",q);return "database";}
 public record ContactForm(@NotBlank @Size(max=100) String name,@NotBlank @Email @Size(max=254) String email,@NotBlank @Size(max=2000) String message){}
 @GetMapping("/contact") public String contact(Model m){m.addAttribute("contactForm",new ContactForm("","",""));return "contact";}
 @PostMapping("/contact") public String contactSave(@Valid @ModelAttribute ContactForm contactForm,BindingResult errors){if(errors.hasErrors())return "contact";ContactMessage message=new ContactMessage();message.setName(contactForm.name());message.setEmail(contactForm.email());message.setMessage(contactForm.message());message.setSentAt(LocalDateTime.now());messages.save(message);return "redirect:/contact?sent";}
 @GetMapping({"/messages","/user"}) public String messages(Model m){m.addAttribute("messages",messages.findAllByOrderBySentAtDescIdDesc());return "messages";}
 @GetMapping("/chart") public String chart(Model m){Map<String,Integer> counts=new TreeMap<>();for(Notebook n:service.notebooks.catalogue())counts.merge(n.getManufacturer(),1,Integer::sum);m.addAttribute("labels",counts.keySet());m.addAttribute("values",counts.values());return "chart";}
 @GetMapping("/rest") public String rest(){return "rest";}
 @GetMapping("/admin") public String admin(Model m){m.addAttribute("notebooks",service.notebooks.catalogue());return "admin";}
 private void options(Model m,Integer id){m.addAttribute("processors",service.processors.findAll());m.addAttribute("systems",service.systems.findAll());m.addAttribute("editId",id);}
 @GetMapping("/admin/new") public String create(Model m){options(m,null);m.addAttribute("notebookForm",new NotebookForm("","",null,null,null,"",null,null,null,null));return "edit";}
 @GetMapping("/admin/{id}/edit") public String edit(@PathVariable Integer id,Model m){options(m,id);m.addAttribute("notebookForm",service.form(service.find(id)));return "edit";}
 @PostMapping({"/admin/new","/admin/{id}/edit"}) public String save(@PathVariable(required=false) Integer id,@Valid @ModelAttribute NotebookForm notebookForm,BindingResult errors,Model m){if(errors.hasErrors()){options(m,id);return "edit";}try{service.save(id,notebookForm);}catch(org.springframework.web.server.ResponseStatusException e){errors.reject("invalid",e.getReason());options(m,id);return "edit";}return "redirect:/admin";}
 @PostMapping("/admin/{id}/delete") public String delete(@PathVariable Integer id){service.delete(id);return "redirect:/admin";}
}

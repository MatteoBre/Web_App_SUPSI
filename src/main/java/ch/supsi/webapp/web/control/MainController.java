package ch.supsi.webapp.web.control;

import ch.supsi.webapp.web.model.Ruolo;
import ch.supsi.webapp.web.model.Utente;
import ch.supsi.webapp.web.service.BlogpostService;
import ch.supsi.webapp.web.model.Blogpost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class MainController {

    @Autowired
    private BlogpostService blogpostService;

    @GetMapping("/")
    public String getBlogposts(Model model) {
        model.addAttribute("blogposts", blogpostService.getBlogposts());
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String getBlogpostId(@PathVariable int id, Model model) {
        model.addAttribute("blogpost", blogpostService.getBlogpostByID(id));
        return "blogpostDetails";
    }

    @GetMapping("/blog/new")
    public String createNewBlogpost(Model model){
        model.addAttribute("categories", blogpostService.getCategories());
        model.addAttribute("blogpost", new Blogpost());
        return "createBlogForm";
    }

    @PostMapping("/blog/new")
    public String postNewBlogpost(@ModelAttribute Blogpost blogpost, Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        blogpost.setUtente(blogpostService.findUserByUsername(user.getUsername()));
        blogpost.setDate(new Date());
        blogpostService.saveBlogpost(blogpost);
        return "redirect:/";
    }

    @GetMapping("/blog/{id}/edit")
    public String getBlogpostToEdit(@PathVariable int id, Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.getUsername().equals(blogpostService.getBlogpostByID(id).getUtente().getUsername()))
            return "redirect:/?error";
        Blogpost blogpost = blogpostService.getBlogpostByID(id);
        model.addAttribute("categories", blogpostService.getCategories());
        model.addAttribute("blogpost", blogpost);
        return "createBlogForm";
    }

    @PostMapping("/blog/{id}/edit")
    public String postBlogpostToEdit(@PathVariable int id,@ModelAttribute Blogpost blogpost, Model model){
        Blogpost vecchio = blogpostService.getBlogpostByID(id);
        blogpost.setDate(vecchio.getDate());
        blogpost.setUtente(vecchio.getUtente());
        blogpost.setId(vecchio.getId());
        blogpostService.modifica(blogpost,id);
        return "redirect:/blog/{id}";
    }

    @GetMapping("/blog/{id}/delete")
    public String deleteBlogpost(@PathVariable int id, Model model){
        blogpostService.elimina(id);
        return "redirect:/";
    }

    @GetMapping("/blog/all")
    public String getAllBlogposts(Model model){
        model.addAttribute("blogposts", blogpostService.getBlogposts());
        return "allBlogposts";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("utente", new Utente());
        model.addAttribute("passwordCheck", "ciao");
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Utente utente, Model model){
        System.out.println(utente.getPassword());
        if(blogpostService.findUserByUsername(utente.getUsername())!=null)
            return "redirect:/register?error";
        Ruolo ruolo = new Ruolo("ROLE_USER");
        utente.setRuolo(ruolo);

        blogpostService.postUser(utente);

        return "redirect:/";
    }

}
